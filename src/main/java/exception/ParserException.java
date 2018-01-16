package exception;

public class ParserException extends Exception {
    public ParserException(){}
    public ParserException(String m){
        super(m);
    }
    public ParserException(String m, Throwable th){
        super(m,th);
    }
    public ParserException(Throwable th){
        super(th);
    }
}
