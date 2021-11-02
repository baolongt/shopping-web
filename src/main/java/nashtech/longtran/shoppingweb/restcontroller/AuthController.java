package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.payload.request.LoginRequest;
import nashtech.longtran.shoppingweb.payload.request.SignupRequest;
import nashtech.longtran.shoppingweb.services.implement.AuthServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImp authServiceImp;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authServiceImp.signin(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return  ResponseEntity.ok(authServiceImp.signup(signUpRequest));
    }

}
