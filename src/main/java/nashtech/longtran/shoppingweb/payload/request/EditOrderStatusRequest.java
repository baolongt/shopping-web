package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EditOrderStatusRequest {
    @NotBlank(message = "Order id may not be blank")
    private int orderID;
    @NotBlank(message = "Status may not be blank")
    private String status;
}
