package app.services.impl;

import app.model.User;
import app.repos.UserRepository;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
