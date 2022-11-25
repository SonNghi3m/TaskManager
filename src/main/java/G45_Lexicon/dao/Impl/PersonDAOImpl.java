package G45_Lexicon.dao.Impl;

import G45_Lexicon.dao.Impl.sequencer.PersonIdSequencer;
import G45_Lexicon.dao.PersonDAO;
import G45_Lexicon.model.Person;
import G45_Lexicon.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//Singleton implementation
//step 1: define a static field with the class type (In this scenario, class type is:  PersonDAOImpl)
//step 2: make constructor private
//step 3: define a static method with if condition to check the object is null or no ( getInstance method)

public class PersonDAOImpl implements PersonDAO {
    //fields
    private List<Person> personList; //create a List to storage person data
    private static PersonDAOImpl instance;

    //constructors
    private PersonDAOImpl() {personList = new ArrayList<>();}
    public static PersonDAOImpl getInstance() {
        if (instance == null) instance = new PersonDAOImpl();
        return instance;
    }
    //methods
    @Override
    public Person create(Person person) {
        if (person == null) throw new IllegalArgumentException("Person was null");
        person.setPersonId(PersonIdSequencer.nextId());
        personList.add(person);
        return person;
    }
    @Override
    public Optional<Person> findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        for (Person person : personList) {
            if (person.getPersonId().equals(id)) return Optional.of(person);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteByID(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        return personList.remove(findById(id));
    }

    @Override
    public void update(Person updatedPerson) {
        if (updatedPerson == null) throw new IllegalArgumentException("updatePerson was null");
        for (Person person : personList) {
            if (person.getPersonId().equals(updatedPerson.getPersonId())) {
                person.setFullName(updatedPerson.getFullName());
                person.setUserName(updatedPerson.getUserName());
                person.setPassword(updatedPerson.getPassword());
                person.setTasks(updatedPerson.getTasks());
                person.setRole(updatedPerson.getRole());
                break;
            }
        }
    }

    @Override
    public List<Person> findAll() {return new ArrayList<>(personList);}

    @Override
    public Optional<Person> findByName(String name) {
        if (name == null) throw new IllegalArgumentException("name was null");
        for (Person person : personList) {
            if (person.getFullName().contains(name)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> findByUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("userName was null");
        for (Person person : personList) {
            if (person.getUserName().equals(userName)) return Optional.of(person);
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findByRole(Role role) {
        if (role == null) throw new IllegalArgumentException("role was null");
        List<Person> filteredList = new ArrayList<>();
        for (Person person : personList) {
            if (person.getRole() == role) {filteredList.add(person);}
        }
        return filteredList;
    }
}
