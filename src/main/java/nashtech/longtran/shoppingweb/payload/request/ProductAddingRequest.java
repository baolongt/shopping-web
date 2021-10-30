package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductAddingRequest {
    @NotBlank(message = "Product's name may not be blank")
    private String productName;
    @NotBlank(message = "Brand may not be blank")
    private String brand;
    @NotBlank(message = "Detail may not be blank")
    private String detail;
    @NotBlank(message = "Brand Id may not be blank")
    private int brandId;
    @NotBlank(message = "Category may not be blank")
    private Set<Integer> categories;
}
