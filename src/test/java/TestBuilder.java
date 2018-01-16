import builder.AbstractPaperBuilder;
import builder.PaperBuilderFactory;
import entity.Paper;
import exception.ParameterException;
import exception.ParserException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestBuilder {
    @Test(expectedExceptions = ParameterException.class)
    public void testOnInvalidParser() throws ParameterException, ParserException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder paperBuilder = factory.createPaperBuilder("wrong");
    }

    @Test
    public void testOnValidParser() throws ParameterException, ParserException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder paperBuilder = factory.createPaperBuilder("sax");
    }

    @Test
    public void testOnValidFilePath() throws ParserException, ParameterException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder paperBuilder = factory.createPaperBuilder("sax");
        paperBuilder.buildSetPapers("files/papers.xml");
    }

    @Test (expectedExceptions = ParserException.class)
    public void testOnInvalidFilePath() throws ParserException, ParameterException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder paperBuilder = factory.createPaperBuilder("sax");
        paperBuilder.buildSetPapers("papers.xml");
    }

    @Test (expectedExceptions = ParserException.class)
    public void testOnIncorrectXML() throws ParserException, ParameterException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder paperBuilder = factory.createPaperBuilder("sax");
        paperBuilder.buildSetPapers("files/incorrect.xml");
    }

    @Test
    public void testOnCorrectParsingData() throws ParserException, ParameterException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder builder1 = factory.createPaperBuilder("sax");
        AbstractPaperBuilder builder2 = factory.createPaperBuilder("dom");
        AbstractPaperBuilder builder3 = factory.createPaperBuilder("stax");

        builder1.buildSetPapers("files/papers.xml");
        builder2.buildSetPapers("files/papers.xml");
        builder3.buildSetPapers("files/papers.xml");

        Set<Paper> set1 = builder1.getPapers();
        Set<Paper> set2 = builder2.getPapers();
        Set<Paper> set3 = builder3.getPapers();

        HashSet<Paper> hashSet1 = (HashSet<Paper>) set1;
        HashSet<Paper> hashSet2 = (HashSet<Paper>) set2;
        HashSet<Paper> hashSet3 = (HashSet<Paper>) set3;
    }
}
