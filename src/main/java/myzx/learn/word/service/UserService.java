package myzx.learn.word.service;

import myzx.learn.word.domain.User;

import java.util.List;

public interface UserService {
    int add(User user);
    int update(User user);
    int deleteByName(String name);
    User findByName(String name);
    List<User> findAll();
}
