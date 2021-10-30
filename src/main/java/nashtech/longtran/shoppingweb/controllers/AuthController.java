package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.enums.ERole;
import nashtech.longtran.shoppingweb.entity.Role;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.request.LoginRequest;
import nashtech.longtran.shoppingweb.payload.request.SignupRequest;
import nashtech.longtran.shoppingweb.payload.response.JwtResponse;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.RoleRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.security.jwt.JwtUtils;
import nashtech.longtran.shoppingweb.services.implement.AuthServiceImp;
import nashtech.longtran.shoppingweb.services.implement.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
