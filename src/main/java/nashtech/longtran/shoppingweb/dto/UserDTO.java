package nashtech.longtran.shoppingweb.dto;

import nashtech.longtran.shoppingweb.entity.Role;

import java.util.Set;

public class UserDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private Set<Role> roles;
}
