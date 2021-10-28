package nashtech.longtran.shoppingweb.exception;

public class BrandIdNotFoundException extends RuntimeException{
    public BrandIdNotFoundException(Integer id) {
        super("Could not found brand's id: "+id);
    }
}
