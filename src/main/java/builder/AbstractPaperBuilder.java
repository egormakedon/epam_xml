package builder;

import entity.Paper;
import exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPaperBuilder {
    protected static final Logger LOGGER = LogManager.getLogger(AbstractPaperBuilder.class);
    protected Set<Paper> papers;

    public AbstractPaperBuilder() {
        papers = new HashSet<Paper>();
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    abstract public void buildSetPapers(String fileName) throws ParserException;
}
