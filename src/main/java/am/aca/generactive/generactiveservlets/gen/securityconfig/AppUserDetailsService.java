package am.aca.generactive.generactiveservlets.gen.securityconfig;

import am.aca.generactive.generactiveservlets.gen.Repository.springrepo.UserRepository;
import am.aca.generactive.generactiveservlets.gen.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsernameEager(username);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        User user = userOpt.get();

        UserDetails userDetails = new AppUserDetails(user);

        return userDetails;
    }
}
