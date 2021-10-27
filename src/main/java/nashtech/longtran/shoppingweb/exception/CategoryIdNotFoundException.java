package nashtech.longtran.shoppingweb.exception;

public class CategoryIdNotFoundException extends RuntimeException{
    public CategoryIdNotFoundException(int id) {
        super("Could not found category's id: "+id);
    }
}
