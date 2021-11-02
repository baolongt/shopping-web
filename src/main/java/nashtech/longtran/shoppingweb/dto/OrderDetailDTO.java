package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderDetailDTO {
    private Integer id;
    @NotBlank(message = "Quantity must not be blank")
    private int quantity;
    @NotBlank(message = "Product detail's id must not be blank")
    private Integer productDetailID;
}
