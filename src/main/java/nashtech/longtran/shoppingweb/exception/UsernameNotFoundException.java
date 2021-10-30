package nashtech.longtran.shoppingweb.exception;

public class UsernameNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UsernameNotFoundException(String username) {
        super("Could not found username: "+username);
    }
}
