package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    @NotBlank(message = "Product's name may not be blank")
    private String productName;
    @NotBlank(message = "Brand may not be blank")
    private String brand;
    @NotBlank(message = "Detail may not be blank")
    private String detail;
    @NotBlank(message = "Brand Id may not be blank")
    private int brandId;
    @NotBlank(message = "Category may not be blank")
    private List<CategoryDTO> categories;
    @NotBlank(message = "Product detail may not be blank")
    private List<ProductDetailDTO> productDetails;
}
