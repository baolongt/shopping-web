package nashtech.longtran.shoppingweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Integer id;
    @NotBlank(message = "Username may not be blank")
    private String username;
    @NotBlank(message = "Address may not be blank")
    private String address;
    @NotBlank(message = "Status may not blank")
    private String status;
    @NotBlank(message = "product may not blank")
    private List<OrderDetailDTO> products;
}
