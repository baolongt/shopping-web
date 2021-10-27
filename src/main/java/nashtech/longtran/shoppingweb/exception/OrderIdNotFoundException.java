package nashtech.longtran.shoppingweb.exception;

public class OrderIdNotFoundException extends RuntimeException{
    public OrderIdNotFoundException(int id) {
        super("Could not found order's id: "+id);
    }
}
