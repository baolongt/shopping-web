package nashtech.longtran.shoppingweb.exception;

public class CategoryIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CategoryIdNotFoundException(String message) {
        super(message);
    }
}
