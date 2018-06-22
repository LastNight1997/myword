package myzx.learn.word.service.impl;

import myzx.learn.word.dao.UserMapper;
import myzx.learn.word.domain.User;
import myzx.learn.word.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int add(User user) {
        return this.userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return this.userMapper.update(user);
    }

    @Override
    public int deleteByName(String name) {
        return this.userMapper.deleteByName(name);
    }

    @Override
    public User findByName(String name) {
        return this.userMapper.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return this.userMapper.findAll();
    }

}
