package nashtech.longtran.shoppingweb.exception;

public class ProductIdNotFoundException extends RuntimeException{
    public ProductIdNotFoundException(int productID) {
        super("Could not find product's id: "+productID);
    }
}
