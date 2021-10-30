package nashtech.longtran.shoppingweb.exception;

public class EmailIsExistedException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EmailIsExistedException(String email) {
        super("Email "+email+" is existed");
    }
}
