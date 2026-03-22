package in.careerit.main.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.careerit.main.auth.LoginResponse;
import in.careerit.main.entities.User;
import in.careerit.main.repositories.UserRepository;
import in.careerit.main.services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody Map<String, Object> body) {

        String username = ((String) body.get("username")).trim();
        String password = ((String) body.get("password")).trim();

        System.out.println("INPUT USERNAME = [" + username + "]");
        System.out.println("INPUT PASSWORD = [" + password + "]");

        User matchedUser = null;

        for (User u : userRepository.findAll()) {

            System.out.println("CHECKING DB USER = [" + u.getUsername() + "]");

            if (u.getUsername() != null &&
                u.getUsername().trim().equalsIgnoreCase(username)) {

                matchedUser = u;
                break;
            }
        }

        if (matchedUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!matchedUser.getPassword().trim().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponse(
                matchedUser.getId(),
                matchedUser.getUsername(),
                matchedUser.getRole()
        );
    }
}