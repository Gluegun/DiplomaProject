package app.service.impl;

import app.dto.UserDetail;
import app.dto.UserDto;
import app.mapper.Mapper;
import app.model.User;
import app.repos.UserRepository;
import app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            return null;
        }

        Optional<User> byEmail = userRepository.findByEmail(username);
        if (byEmail.isPresent()) {

            UserDto user = mapper.toUserDto(byEmail.get());
            return new UserDetail(user);

        } else return null;
    }
}
