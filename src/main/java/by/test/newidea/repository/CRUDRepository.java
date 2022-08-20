package by.test.newidea.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CRUDRepository<K,T> {
    int DEFAULT_FIND_ALL_LIMIT = 10;
    int DEFAULT_FIND_ALL_OFFSET = 0;

    T findById(K id);
    Optional<T> findOne(K id);
    List<T> findAll();
    List<T> findAll(int limit, int offset);
    T create(T object);
    T update(T object);
    K delete(K id);
    List<T> findNameSurname(String search);
    List<T> findNameSurnameParam(String search);


}
