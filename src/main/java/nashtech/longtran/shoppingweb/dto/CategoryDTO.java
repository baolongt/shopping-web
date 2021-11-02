package nashtech.longtran.shoppingweb.dto;

import javax.validation.constraints.NotBlank;

public class CategoryDTO {
    Integer id;
    @NotBlank(message = "Name must not be blank")
    String name;
    Integer parentID;
}
