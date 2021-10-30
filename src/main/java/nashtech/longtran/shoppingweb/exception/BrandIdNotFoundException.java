package nashtech.longtran.shoppingweb.exception;

public class BrandIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public BrandIdNotFoundException(Integer id) {
        super("Could not found brand's id: "+id);
    }
}
