package builder;

import constant.PaperType;
import entity.Booklet;
import entity.Journal;
import entity.Newspaper;
import entity.Paper;
import exception.MethodNotSupportedException;
import exception.ParserException;
import org.apache.logging.log4j.Level;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PaperSAXBuilder extends AbstractPaperBuilder {
    private Handler handler;

    PaperSAXBuilder() {
        handler = new Handler();
    }

    private class Handler extends DefaultHandler {
        private final String PAPER_ID = PaperType.PAPER_ID.getValue();
        private final String SUBSCRIPTION_INDEX = PaperType.SUBSCRIPTION_INDEX.getValue();
        private final String TITTLE = PaperType.TITTLE.getValue();
        private final String GLOSS = PaperType.GLOSS.getValue();

        private final String MOTHLY = PaperType.MOTHLY.getValue();
        private final String COLOR = PaperType.COLOR.getValue();
        private final String VOLUME = PaperType.COLOR.getValue();

        private Paper paper;
        private String qName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs) {
            this.qName = qName;

            if (qName.equals(PaperType.JOURNAL.getValue())) {
                paper = new Journal();
            }
            if (qName.equals(PaperType.NEWSPAPER.getValue())) {
                paper = new Newspaper();
            }
            if (qName.equals(PaperType.BOOKLET.getValue())) {
                paper = new Booklet();
            }
            for (int index = 0; index < attrs.getLength(); index++) {
                String attribute = attrs.getLocalName(index);
                String value = attrs.getValue(index);
                try {
                    attributeFactory(attribute, value);
                } catch (ParserException e) {
                    LOGGER.log(Level.WARN, e);
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String value = new String(ch, start, length).trim();
            if (qName.equals(MOTHLY)) {
                paper.setMothly(Boolean.parseBoolean(value));
            } else if (qName.equals(COLOR)) {
                paper.setColor(Boolean.parseBoolean(value));
            } else if (qName.equals(VOLUME)) {
                paper.setVolume(Integer.parseInt(value));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals(PaperType.JOURNAL.getValue()) || qName.equals(PaperType.BOOKLET.getValue()) ||
                    qName.equals(PaperType.NEWSPAPER.getValue())) {
                papers.add(paper);
            }
        }

        private void attributeFactory(String attribute, String value) throws ParserException {
            try {
                if (attribute.equals(PAPER_ID)) {
                    paper.setId(value);
                } else if (attribute.equals(SUBSCRIPTION_INDEX)) {
                    paper.setSubscriptionIndex(Integer.parseInt(value));
                } else if (attribute.equals(TITTLE)) {
                    paper.setTittle(value);
                } else if (attribute.equals(GLOSS)) {
                    paper.setGloss(Boolean.parseBoolean(value));
                } else {
                    throw new ParserException("unknown parameter");
                }
            } catch (MethodNotSupportedException e) {
                LOGGER.log(Level.WARN, e);
            }
        }
    }

    @Override
    public void buildSetPapers(String fileName) throws ParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(new File(fileName), handler);
        } catch (FileNotFoundException e) {
            throw new ParserException("file not found", e);
        } catch (SAXException e) {
            throw new ParserException("sax exception", e);
        } catch (IOException e) {
            throw new ParserException("io exception", e);
        } catch (ParserConfigurationException e) {
            throw new ParserException("config exception", e);
        }
    }
}
