package G45_Lexicon.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    T create(T model);
    boolean deleteByID(Integer id);
    T update(T model);
    List<T> findAll();
    T findById(Integer id);

}

