package exception;

public class ParameterException extends Exception {
    public ParameterException(){}
    public ParameterException(String m){
        super(m);
    }
    public ParameterException(String m, Throwable th){
        super(m,th);
    }
    public ParameterException(Throwable th){
        super(th);
    }
}
