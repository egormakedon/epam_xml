package exception;

public class MethodNotSupportedException extends Exception {
    public MethodNotSupportedException(){}
    public MethodNotSupportedException(String m){
        super(m);
    }
    public MethodNotSupportedException(String m, Throwable th){
        super(m,th);
    }
    public MethodNotSupportedException(Throwable th){
       super(th);
    }

}
