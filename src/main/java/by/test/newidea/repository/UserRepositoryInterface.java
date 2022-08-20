package by.test.newidea.repository;

import by.test.newidea.domain.User;

import java.util.Map;

public interface UserRepositoryInterface extends CRUDRepository<Long, User>{

    Map<String, Object> getUserStats();

}
