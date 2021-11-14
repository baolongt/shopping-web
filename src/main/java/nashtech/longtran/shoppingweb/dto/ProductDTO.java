package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    @NotBlank(message = "Product's name may not be blank")
    private String name;
    @NotBlank(message = "Brand's ID may not be blank")
    private Integer brandId;
    private String brandName;
    private String detail;
    @NotBlank(message = "Category may not be blank")
    private ProductCategoryDTO category;
    private Set<ProductDetailDTO> productDetails;
    private Set<String> colors;
    private Set<String> sizes;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", detail='" + detail + '\'' +
                ", category=" + category +
                ", productDetails=" + productDetails +
                ", colors=" + colors +
                ", sizes=" + sizes +
                '}';
    }
}
