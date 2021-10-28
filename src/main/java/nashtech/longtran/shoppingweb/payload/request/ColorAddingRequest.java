package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ColorAddingRequest {
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotBlank(message = "Color hex may not be blank")
    private String colorHex;
}
