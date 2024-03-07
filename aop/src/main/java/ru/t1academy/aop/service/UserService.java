package ru.t1academy.aop.service;

import org.springframework.transaction.annotation.Transactional;
import ru.t1academy.aop.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserId(Integer id);

    List<User> getAllUsers();

    void deleteUserById(Integer id);

    void updateUserById(Integer id, User user);

    @Transactional
    User getIfExistUserById(Integer id);
}
