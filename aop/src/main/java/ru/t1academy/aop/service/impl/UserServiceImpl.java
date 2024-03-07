package ru.t1academy.aop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.t1academy.aop.entity.Order;
import ru.t1academy.aop.entity.User;
import ru.t1academy.aop.exception.NoUniqueUserEmailException;
import ru.t1academy.aop.exception.NoUserExistException;
import ru.t1academy.aop.repository.UserRepository;
import ru.t1academy.aop.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        checkUniqueEmail(user.getEmail());
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserId(Integer id) {
        return getIfExistUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = getIfExistUserById(id);
        for (Order order : user.getOrders()) {
            order.setUser(null);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUserById(Integer id, User user) {
        User updatableUser = getIfExistUserById(id);
        updateUser(updatableUser, user);
        userRepository.save(updatableUser);
    }

    @Override
    @Transactional
    public User getIfExistUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoUserExistException("User with id={id} not found"));
    }

    private void updateUser(User updatableUser, User user) {
        if (Objects.nonNull(user.getEmail()) && StringUtils.hasLength(user.getEmail())) {
            checkUniqueEmail(user.getEmail());
            updatableUser.setEmail(user.getEmail());
        }

        if (Objects.nonNull(user.getName()) && StringUtils.hasLength(user.getName())) {
            updatableUser.setName(user.getName());
        }
    }

    private void checkUniqueEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new NoUniqueUserEmailException("User with email = {email} already exist");
        }
    }

}

