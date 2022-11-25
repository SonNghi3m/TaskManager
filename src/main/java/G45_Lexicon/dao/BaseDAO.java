package G45_Lexicon.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    T create(T model);
    boolean deleteByID(Integer id);
    void update(T model);
    List<T> findAll();
    Optional<T> findById(Integer id);

}

