package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditOrderStatusRequest {
    private int orderID;
    private String status;
}
