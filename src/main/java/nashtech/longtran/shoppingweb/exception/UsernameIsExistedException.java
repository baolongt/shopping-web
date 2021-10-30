package nashtech.longtran.shoppingweb.exception;

public class UsernameIsExistedException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UsernameIsExistedException(String username) {
        super("Username "+ username+" is existed");
    }
}
