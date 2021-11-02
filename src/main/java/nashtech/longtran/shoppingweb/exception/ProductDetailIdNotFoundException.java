package nashtech.longtran.shoppingweb.exception;

public class ProductDetailIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductDetailIdNotFoundException(String message) {
        super(message);
    }
}
