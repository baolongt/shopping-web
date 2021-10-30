package nashtech.longtran.shoppingweb.exception;

public class SizeIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public SizeIdNotFoundException(Integer id) {
        super("Could not find size's id: "+id);
    }
}
