package G45_Lexicon.dao;

import G45_Lexicon.model.Person;
import G45_Lexicon.model.Role;

import java.util.List;
import java.util.Optional;

public interface PersonDAO extends BaseDAO<Person>{
    List<Person> findByName(String name);
    Optional<Person> findByUserName(String userName);
    List<Person> findByRole(Role role);
}
