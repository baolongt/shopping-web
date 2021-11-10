package nashtech.longtran.shoppingweb.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponse {
    private Integer id;
    private String name;
    private List<SubCategoryResponse> subCategories;
}
