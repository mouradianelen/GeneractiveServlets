package am.aca.generactive.generactiveservlets.gen.service;

import am.aca.generactive.generactiveservlets.gen.Repository.springrepo.UserRepository;
import am.aca.generactive.generactiveservlets.gen.model.User;
import am.aca.generactive.generactiveservlets.gen.securityconfig.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getCurrentUser() {
        return userRepository.findByUsername(SecurityUtils.getAuthentication().getUsername());
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
