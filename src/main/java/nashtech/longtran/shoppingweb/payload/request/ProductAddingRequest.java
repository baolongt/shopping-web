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
    @NotBlank(message = "Price may not be blank")
    private float price;
    @NotBlank(message = "Quantity may not be blank")
    private int quantity;
    @NotBlank(message = "Brand may not be blank")
    private String brand;
    @NotBlank(message = "Detail may not be blank")
    private String detail;
    @NotBlank(message = "Category may not be blank")
    private Set<Integer> categories;
}
