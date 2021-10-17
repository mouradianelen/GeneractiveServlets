package am.aca.generactive.generactiveservlets.gen.controller;

import am.aca.generactive.generactiveservlets.gen.model.User;
import am.aca.generactive.generactiveservlets.gen.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String getCurrentUserUsername() {
        Optional<User> user = userService.getCurrentUser();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("no current user");
        }
        return user.get().getUsername();
    }
}

