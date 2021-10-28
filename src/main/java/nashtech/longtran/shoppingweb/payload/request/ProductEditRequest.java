package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class ProductEditRequest {
    @NotBlank
    private Integer id;
    @NotBlank
    private String productName;
    private float price;
    private int quantity;
    private String brand;
    private String detail;
    private String imgURL = "";
    private int brandId;
    private Set<Integer> categories;
}
