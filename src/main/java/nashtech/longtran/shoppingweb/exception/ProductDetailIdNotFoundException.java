package nashtech.longtran.shoppingweb.exception;

public class ProductDetailIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ProductDetailIdNotFoundException(Integer id) {
        super("Could not find product detail's id: "+id);
    }
}
