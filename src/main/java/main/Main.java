package main;

import builder.AbstractPaperBuilder;
import builder.PaperBuilderFactory;
import exception.ParameterException;
import exception.ParserException;

public class Main {
    public static void main(String[] args) throws ParameterException, ParserException {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder paperBuilder = factory.createPaperBuilder("stax");
        paperBuilder.buildSetPapers("files/papers.xml");
    }
}
