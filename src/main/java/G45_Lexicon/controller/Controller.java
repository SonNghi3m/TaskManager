package G45_Lexicon.controller;

import G45_Lexicon.dao.Impl.PersonDAOImpl;
import G45_Lexicon.dao.Impl.TaskDAOImpl;
import G45_Lexicon.dao.PersonDAO;
import G45_Lexicon.dao.TaskDAO;
import G45_Lexicon.model.Person;
import G45_Lexicon.model.Task;
import G45_Lexicon.view.ConsoleUI;
import G45_Lexicon.view.MenuOption;

import java.util.List;
import java.util.Objects;

public class Controller {
    //fields
    ConsoleUI ui;
    PersonDAOImpl personDAO = new PersonDAOImpl();
    TaskDAOImpl taskDAO = new TaskDAOImpl();

    //constructors
    public Controller(){ui = new ConsoleUI();}

    //methods
    public void doMainMenu() {
        while (true) {
            MenuOption option = ui.displayMenu();
            switch (option) {
                case ADD_TASK:
                    doAddTask();
                    break;
                case ADD_PERSON:
                    doAddPerson();
                    break;
                case DELETE_PERSON_BY_PERSON_ID:
                    deletePerson();
                    break;
                case DELETE_TASK_BY_TASK_ID:
                    deleteTask();
                    break;
                case UPDATE_PERSON_BY_PERSON_ID:
                    updatePerson();
                    break;
                case UPDATE_TASK_BY_TASK_ID:
                    updateTask();
                    break;
                case DISPLAY_ALL_TASKS:
                    displayAllTask();
                    break;
                case DISPLAY_ALL_PEOPLE:
                    displayAllPeople();
                    break;
                case FIND_TASKS_BY_PERSON_ID:
                    findTasksByPersonId();
                    break;
                case FIND_TASKS_BY_TASK_ID:
                    findTaskById();
                    break;
                case FIND_TASKS_BY_USERNAME:
                    findTasksByUsername();
                    break;
                case FIND_TASKS_BY_ROLE:
                    findTasksByRole();
                    break;
                case FIND_TASKS_BY_DONE_STATUS:
                    findTasksByDoneStatus();
                    break;
                case FIND_TASKS_BY_TITLE:
                    findTasksByTitle();
                    break;
                case QUIT: System.exit(0);
            } //switch
        } // while
    } //doMainMenu

    public void doAddTask() {
    Task taskInput = ui.getTaskData();
    taskDAO.create(taskInput);
    }

    public void doAddPerson() {
    Person personInput = ui.getPersonData();
    personDAO.create(personInput);
    }

    public void deleteTask() {
        System.out.println("Enter task ID:");
        int id = ui.getInt();
        taskDAO.deleteByID(id);
    }
    public void deletePerson() {
        System.out.println("Enter person ID:");
        int id = ui.getInt();
        personDAO.deleteByID(id);
    }

    public void updatePerson() {
        Person updatedPerson = ui.updatePersonData();
        personDAO.update(updatedPerson);
    }

    public void updateTask() {
        Task updatedTask = ui.updateTaskData();
        taskDAO.update(updatedTask);
    }

    public void findTasksByPersonId() {
        System.out.println("Enter person ID:");
        int id = ui.getPersonIdData();
        taskDAO.findByPersonId(id);
    }

    public void findTaskById() {
        System.out.println("Enter task ID:");
        int id = ui.getInt();
        taskDAO.findById(id);
    }

    public void findTasksByUsername() {
        System.out.println("Enter username:");
        String username = ui.getString();
        taskDAO.findByUsername(username);
    }

    public void findTasksByRole() {
        System.out.println("Enter role (user/admin):");
        String role = ui.getString().toUpperCase();
        taskDAO.findByRole(role);
    }

    public void findTasksByDoneStatus() {
        System.out.println("Enter done status(true/false):");
        String done = ui.getString();
        taskDAO.findByDoneStatus(Boolean.parseBoolean(done));
    }

    public void findTasksByTitle() {
        System.out.println("Enter title:");
        String title = ui.getString();
        taskDAO.findByTitle(title);
    }
    public void displayAllPeople() {
        personDAO.findAll();
    }

    public void displayAllTask() {
        taskDAO.findAll();
    }
} // class Controller
