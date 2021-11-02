package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.services.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
