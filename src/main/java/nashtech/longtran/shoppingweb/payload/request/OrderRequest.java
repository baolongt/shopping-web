package nashtech.longtran.shoppingweb.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class OrderRequest{
    @NotBlank
    private String username;
    @NotBlank
    private String address;
    @NotBlank
    private Set<OrderProductRequest> products;
}

