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

    @NotBlank
    private String productName;
    private float price = 0;
    private int quantity = 0;
    private String brand = "";
    private String detail = "";
    private Set<Integer> categories;

}
