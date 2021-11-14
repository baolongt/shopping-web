package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryDTO {
    private Integer parentID;
    private String pCategoryName;
    private Integer categoryID;
    private String CategoryName;
}
