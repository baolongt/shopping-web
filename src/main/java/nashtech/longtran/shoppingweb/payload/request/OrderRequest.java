package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class OrderRequest{
    @NotBlank(message = "username may not be blank")
    private String username;
    @NotBlank(message = "address may not be blank")
    private String address;
    @NotBlank(message = "product may not blank")
    private Set<OrderProductRequest> products;
}

