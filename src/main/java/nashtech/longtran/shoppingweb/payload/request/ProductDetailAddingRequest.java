package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDetailAddingRequest {
    @NotBlank(message = "Product's id must not be blank")
    private Integer productID;
    @NotBlank(message = "Color's id must not be blank")
    private Integer colorId;
    @NotBlank(message = "Size's id must not be blank")
    private Integer sizeId;
    @NotBlank(message = "Quantity must not be blank")
    private Integer quantity;
    @NotBlank(message = "Price must not be blank")
    private Float price;
}
