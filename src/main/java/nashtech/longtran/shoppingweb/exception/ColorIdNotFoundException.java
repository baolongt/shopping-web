package nashtech.longtran.shoppingweb.exception;

import java.util.function.Supplier;

public class ColorIdNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ColorIdNotFoundException(String message) {
        super(message);
    }
}
