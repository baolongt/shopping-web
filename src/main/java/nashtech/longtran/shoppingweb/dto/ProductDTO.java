package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    @NotBlank(message = "Product's name may not be blank")
    private String name;
    @NotBlank(message = "Brand may not be blank")
    private BrandDTO brand;
    private String detail;
    @NotBlank(message = "Category may not be blank")
    private Set<CategoryDTO> categories;
    private Set<ProductDetailDTO> productDetails;
}
