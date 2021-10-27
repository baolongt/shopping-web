package nashtech.longtran.shoppingweb.service.implement;

import nashtech.longtran.shoppingweb.dto.UserDTO;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.request.UserInfoEditRequest;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDTO getUserInfo(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updatePassword(String password) {
        return null;
    }

    @Override
    public UserDTO updateEmail(String email) {
        return null;
    }

    @Override
    public UserDTO editUserInfo(UserInfoEditRequest request) {
        return null;
    }

}
