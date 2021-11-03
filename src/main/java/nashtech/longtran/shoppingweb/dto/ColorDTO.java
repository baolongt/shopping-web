package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ColorDTO {
    Integer id;
    @NotBlank(message = "Name must not be blank")
    String name;
    @NotBlank(message = "Color hex must not be blank")
    String colorHex;
}
