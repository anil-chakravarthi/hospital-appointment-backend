package in.careerit.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.entities.User;
import in.careerit.main.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {

        System.out.println("INPUT USERNAME = [" + username + "]");
        System.out.println("INPUT PASSWORD = [" + password + "]");

        for (User u : userRepository.findAll()) {

            System.out.println("DB USERNAME = [" + u.getUsername() + "]");
            System.out.println("DB PASSWORD = [" + u.getPassword() + "]");

            if (u.getUsername().trim().equalsIgnoreCase(username.trim())) {

                System.out.println("USERNAME MATCH FOUND");

                if (u.getPassword().trim().equals(password.trim())) {
                    System.out.println("PASSWORD MATCH SUCCESS");
                    return u;
                } else {
                    System.out.println("PASSWORD MISMATCH");
                }
            }
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}