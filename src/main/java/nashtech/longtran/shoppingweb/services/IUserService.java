package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.UserDTO;
import nashtech.longtran.shoppingweb.payload.request.UserInfoEditRequest;

public interface IUserService {
    UserDTO getUserInfo(String username);
    UserDTO updatePassword(String password);
    UserDTO updateEmail(String email);
    UserDTO editUserInfo(UserInfoEditRequest request);
}
