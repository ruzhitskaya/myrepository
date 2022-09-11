package by.test.newidea.service;

import by.test.newidea.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User create(User object);
    User findById(Long userId);

    List<User> search(int limit, int offset);


}
