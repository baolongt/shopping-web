package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryEditRequest {
    @NotBlank(message = "Category's id must not be blank")
    Integer categoryID;
    @NotBlank(message = "Name must not be blank")
    String name;
    @NotBlank(message = "Parent's id must not be blank")
    Integer parentID;
}
