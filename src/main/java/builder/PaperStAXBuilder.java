package builder;

import constant.PaperType;
import entity.Booklet;
import entity.Journal;
import entity.Newspaper;
import entity.Paper;
import exception.MethodNotSupportedException;
import exception.ParserException;
import org.apache.logging.log4j.Level;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PaperStAXBuilder extends AbstractPaperBuilder {
    private XMLInputFactory inputFactory;

    PaperStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetPapers(String fileName) throws ParserException {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PaperType.NEWSPAPER.getValue().equals(name)) {
                        Paper paper = buildNewsPaperOrJournal(reader, PaperType.NEWSPAPER);
                        papers.add(paper);
                    } else if (PaperType.JOURNAL.getValue().equals(name)) {
                        Paper paper = buildNewsPaperOrJournal(reader, PaperType.JOURNAL);
                        papers.add(paper);
                    } else if (PaperType.BOOKLET.getValue().equals(name)) {
                        Paper paper = buildBooklet(reader);
                        papers.add(paper);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new ParserException("StAX parsing error!", e);
        } catch (FileNotFoundException e) {
            throw new ParserException("File " + fileName + " not found!", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new ParserException("Impossible close file " + fileName, e);
            }
        }
    }

    private Paper buildNewsPaperOrJournal(XMLStreamReader reader, PaperType paperType) {
        Paper paper;
        if (paperType == PaperType.JOURNAL) {
            paper = new Journal();
        } else {
            paper = new Newspaper();
        }

        paper = buildSamePart(paper, reader);

        try {
            int subscriptionIndex = Integer.parseInt(reader.getAttributeValue(null, PaperType.SUBSCRIPTION_INDEX.getValue()));
            paper.setSubscriptionIndex(subscriptionIndex);
        } catch (MethodNotSupportedException e) {
            LOGGER.log(Level.ERROR, e);
        }

        try {
            paper = buildElements(paper, reader);
        } catch (ParserException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return paper;
    }

    private Paper buildBooklet(XMLStreamReader reader) {
        Paper paper = new Booklet();
        paper = buildSamePart(paper, reader);
        try {
            paper = buildElements(paper, reader);
        } catch (ParserException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return paper;
    }

    private Paper buildSamePart(Paper paper, XMLStreamReader reader) {
        String paperID = reader.getAttributeValue(null, PaperType.PAPER_ID.getValue());
        paper.setId(paperID);
        String tittle = reader.getAttributeValue(null, PaperType.TITTLE.getValue());
        paper.setTittle(tittle);
        try {
            String gloss = reader.getAttributeValue(null, PaperType.GLOSS.getValue());
            if (gloss != null) {
                paper.setGloss(Boolean.parseBoolean(gloss));
            }
        } catch (MethodNotSupportedException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return paper;
    }

    private Paper buildElements(Paper paper, XMLStreamReader reader) throws ParserException {
        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        int subType = type;
                        while (reader.hasNext() && subType != XMLStreamConstants.CHARACTERS) {
                            subType = reader.next();
                        }
                        switch (PaperType.valueOf(name.toUpperCase())) {
                            case MOTHLY:
                                paper.setMothly(Boolean.parseBoolean(reader.getText()));
                                System.out.println(reader.getText());
                                break;
                            case COLOR:
                                paper.setColor(Boolean.parseBoolean(reader.getText()));
                                break;
                            case VOLUME:
                                paper.setVolume(Integer.parseInt(reader.getText()));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (PaperType.NEWSPAPER.getValue().equals(name) || PaperType.JOURNAL.getValue().equals(name) ||
                                PaperType.BOOKLET.getValue().equals(name)) {
                            return paper;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new ParserException(e);
        }
        throw new ParserException("Unknown element in tag");
    }
}
