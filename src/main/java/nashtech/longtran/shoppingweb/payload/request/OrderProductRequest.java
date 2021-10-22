package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderProductRequest {
    @NotBlank
    private int quantity;
    @NotBlank
    private Integer productID;
}
