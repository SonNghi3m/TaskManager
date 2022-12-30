package G45_Lexicon.model;

import java.util.Objects;

public class Person {
    //fields
    private Integer personId;
    private String fullName;
    private String userName;
    private String password;
    private Task tasks;
    private Role role;


    //constructors
    public Person() {}
    public Person(String fullName, String userName, String password, Role role) {
        this();
        setFullName(fullName);
        setUserName(userName);
        setPassword(password);
        setRole(role);
    }
    public Person(Integer personId, String fullName, String userName, String password, Role role) {
        this(fullName, userName, password, role);
        setPersonId(personId);
    }
    public Person(String fullName, String userName, String password, Task tasks, Role role) {
        this(fullName, userName, password, role);
        setTasks(tasks);
    }
    public Person(Integer personId, String fullName, String userName, String password, Task tasks, Role role) {
        this(fullName, userName, password, tasks, role);
        setPersonId(personId);
    }


    // methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && fullName.equals(person.fullName) && userName.equals(person.userName) && tasks.equals(person.tasks) && role == person.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, fullName, userName, tasks, role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", tasks=" + tasks +
                ", role=" + role +
                '}';
    }

    //getters & setters

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        if (personId == null) throw new RuntimeException("personId was null");
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null) throw new IllegalArgumentException("fullName was null");
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("userName was null");
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new IllegalArgumentException("password was null");
        if (password.length() < 8) throw new IllegalArgumentException("password length was not valid");
        this.password = password;
    }

    public Task getTasks() {
        return tasks;
    }

    public void setTasks(Task tasks) {
        this.tasks = tasks;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
