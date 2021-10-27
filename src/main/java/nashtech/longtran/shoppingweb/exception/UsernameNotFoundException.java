package nashtech.longtran.shoppingweb.exception;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username) {
        super("Could not found username: "+username);
    }
}
