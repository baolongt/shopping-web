package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RatingRequest {
    @NotBlank
    private String username;

    @NotBlank
    private Integer productID;

    @NotBlank
    private Float ratingPoint;

    private String ratingContent;
}
