package app.controllers;

import app.dto.UserDTO;
import app.mappers.UserMapper;
import app.model.User;
import app.repos.UserRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public List<UserDTO> getAllUsersDTO() {
        List<User> users = userRepository.findAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(getUserDTOById(user.getId()));
        }
        return usersDTO;
    }


    @GetMapping("/{id}")
    public UserDTO getUserDTOById(@PathVariable int id) {
        User user = userService.getUser(id);
        return userMapper.toDTO(user);
    }
}
