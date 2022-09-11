package by.test.newidea.service;

import by.test.newidea.domain.User;
import by.test.newidea.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    @Autowired
//    @Qualifier("userRepository");

//    @Inject
//    @Named("userRerository")
//JSR-330

    private final UserRepositoryInterface userRerository;

//        public UserServiceImpl(@Qualifier("userRerository") UserRepositoryInterface userRerository) {
//        this.userRerository = userRerository;
//    }

    @Override
    public List<User> findAll() {
        return userRerository.findAll();
    }

    @Override
    public User create(User object) {
        return userRerository.create(object);

    }

    @Override
    public User findById(Long userId) {
        return userRerository.findById(userId);
    }
    //    @Service
//    @RequiredArgsConstructor
//    public class UserServiceImpl implements UserService {

//    @Autowired
//    @Qualifier("userRepository")

        //    @Inject
//    @Named("userRepository")
//JSR-330
     //   private   UserRepositoryInterface userRepository;

//    public UserServiceImpl(@Qualifier("userRepository") UserRepositoryInterface userRepository) {
//        this.userRepository = userRepository;
//    }

//           @Autowired
//    //@Inject
//        public void setUserRepository(@Qualifier("userRepository") UserRepositoryInterface userRepository) {
//            this.userRepository = userRepository;
//        }


    @Override
    public List<User> search(int limit, int offset) {
        return userRerository.findAll(limit, offset);
    }


}
