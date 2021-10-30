package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Role;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.enums.ERole;
import nashtech.longtran.shoppingweb.exception.EmailIsExistedException;
import nashtech.longtran.shoppingweb.exception.UsernameIsExistedException;
import nashtech.longtran.shoppingweb.payload.request.LoginRequest;
import nashtech.longtran.shoppingweb.payload.request.SignupRequest;
import nashtech.longtran.shoppingweb.payload.response.JwtResponse;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.RoleRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.security.jwt.JwtUtils;
import nashtech.longtran.shoppingweb.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthServiceImp implements IAuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public JwtResponse signin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public User signup(SignupRequest request) throws UsernameIsExistedException, EmailIsExistedException {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameIsExistedException(request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailIsExistedException(request.getEmail());
        }

        // Create new user's account
        User user = new User(request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));
        Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
