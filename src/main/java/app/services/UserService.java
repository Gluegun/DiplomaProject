package app.services;

import app.model.User;

import java.util.List;

public interface UserService {
    User getUser(int id);
    List<User> findAll();
}
