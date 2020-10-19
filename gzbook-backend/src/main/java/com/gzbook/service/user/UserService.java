package com.gzbook.service.user;

import com.gzbook.model.user.User;
import com.gzbook.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).get();
        user.setPassword("mơ đi mà lấy");
        return user  ;
    }

    @Override
    public Iterable<User> findUserByName(String name, Long idLogin) {
        return userRepository.findAllByUsernameContainingAndIdNot(name,idLogin);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
