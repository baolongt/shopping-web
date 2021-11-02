package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BrandDTO {
    private Integer id;
    @NotBlank(message = "Name may not be blank")
    private String name;
}
