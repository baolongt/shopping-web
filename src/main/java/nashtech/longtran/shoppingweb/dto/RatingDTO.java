package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RatingDTO {
    @NotBlank
    private String username;

    @NotBlank
    private Integer productID;

    @NotBlank
    private Float ratingPoint;

    private String ratingContent;
}
