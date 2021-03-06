package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDetailDTO {
    private Integer id;
    @NotBlank(message = "Product's id must not be blank")
    private Integer productID;
    @NotBlank(message = "Color must not be blank")
    private String color;
    @NotBlank(message = "Size must not be blank")
    private String size;
    @NotBlank(message = "Quantity must not be blank")
    private Integer quantity;
    @NotBlank(message = "Price must not be blank")
    private Float price;
    private String imgURL;
}
