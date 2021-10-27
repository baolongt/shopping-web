package nashtech.longtran.shoppingweb.restController;

import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.service.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user/", method = RequestMethod.GET)
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/info/{username}")
    public ResponseEntity<?> getInfo(@PathVariable String username){
        return ResponseEntity.ok(userServiceImp.getUserInfo(username));
    }
}
