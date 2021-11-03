package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryDTO {
    Integer id;
    @NotBlank(message = "Name must not be blank")
    String name;
    Integer parentID;
}
