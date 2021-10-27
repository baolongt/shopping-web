package nashtech.longtran.shoppingweb.payload.request;

import javax.validation.constraints.NotBlank;

public class UserInfoEditRequest {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;
}
