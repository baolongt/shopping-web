package nashtech.longtran.shoppingweb.exception;

public class CategoryIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public CategoryIdNotFoundException(int id) {
        super("Could not found category's id: "+id);
    }
}
