package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;
import nashtech.longtran.shoppingweb.dto.BrandDTO;
import nashtech.longtran.shoppingweb.dto.CategoryDTO;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class ProductAddingRequest {
    @NotBlank(message = "Product's name may not be blank")
    private String name;
    @NotBlank(message = "Brand may not be blank")
    private Integer brandID;
    private String detail;
    @NotBlank(message = "Category may not be blank")
    private Integer categoryID;
}
