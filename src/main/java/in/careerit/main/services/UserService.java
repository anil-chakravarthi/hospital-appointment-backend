package in.careerit.main.services;

import java.util.List;
import in.careerit.main.entities.User;

public interface UserService {

    User register(User user);

    User login(String username, String password);

    List<User> getAllUsers();
}