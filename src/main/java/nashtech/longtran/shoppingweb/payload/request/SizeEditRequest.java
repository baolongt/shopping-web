package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SizeEditRequest {
    @NotBlank(message = "Size's id may not be blank")
    private Integer id;
    @NotBlank(message = "Name may not be blank")
    private String name;
}
