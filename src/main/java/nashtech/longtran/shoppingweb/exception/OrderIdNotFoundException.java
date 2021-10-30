package nashtech.longtran.shoppingweb.exception;

public class OrderIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public OrderIdNotFoundException(int id) {
        super("Could not found order's id: "+id);
    }
}
