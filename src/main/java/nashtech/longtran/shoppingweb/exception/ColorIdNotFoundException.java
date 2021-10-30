package nashtech.longtran.shoppingweb.exception;

public class ColorIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ColorIdNotFoundException(Integer id) {
        super("Could not found brand's id:"+id);
    }
}
