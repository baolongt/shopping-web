package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.exception.EmailIsExistedException;
import nashtech.longtran.shoppingweb.exception.UsernameIsExistedException;
import nashtech.longtran.shoppingweb.payload.request.LoginRequest;
import nashtech.longtran.shoppingweb.payload.request.SignupRequest;
import nashtech.longtran.shoppingweb.payload.response.JwtResponse;

public interface IAuthService {
    JwtResponse signin(LoginRequest request);
    User signup(SignupRequest  request) throws UsernameIsExistedException, EmailIsExistedException;
}
