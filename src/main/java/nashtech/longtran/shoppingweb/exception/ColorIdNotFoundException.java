package nashtech.longtran.shoppingweb.exception;

public class ColorIdNotFoundException extends RuntimeException{
    public ColorIdNotFoundException(Integer id) {
        super("Could not found brand's id:"+id);
    }
}
