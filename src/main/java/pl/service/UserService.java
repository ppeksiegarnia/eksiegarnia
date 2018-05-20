package pl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.model.User;
import pl.model.UserRoles;
import pl.repository.UserRepository;
import pl.repository.UserRolesRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserRolesRepository userRolesRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRolesRepository(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public void addWithDefaultRole(User user){
        UserRoles userRoless=userRolesRepository.findFirstByRole("DEFAULT_ROLE");
        user.getUserRolesSet().add(userRoless);
        userRepository.save(user);
    }

}
