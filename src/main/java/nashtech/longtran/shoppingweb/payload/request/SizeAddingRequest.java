package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SizeAddingRequest {
    @NotBlank(message = "name may not be blank")
    private String name;
}
