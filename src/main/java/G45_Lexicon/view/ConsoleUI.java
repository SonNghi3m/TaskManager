package G45_Lexicon.view;

import G45_Lexicon.dao.Impl.PersonDAOImpl;
import G45_Lexicon.dao.Impl.sequencer.PersonIdSequencer;
import G45_Lexicon.model.Person;
import G45_Lexicon.model.Role;
import G45_Lexicon.model.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class  ConsoleUI {
    /**
     * APP FUNCTIONS
     * 0. Quit
     * 1. Add new task
     * 2. Add new person
     * 3. Delete person by person ID
     * 4. Delete task by task ID
     * 5. Update person by person ID
     * 6. Update task by task ID
     * 7. Display all tasks
     * 8. Display all people
     * 9. Find tasks by person ID
     * 10. Find tasks by task ID
     * 11. Find tasks by username
     * 12. Find tasks by role
     * 13. Find tasks by Done status
     * 14. Find tasks by title
     */

    public MenuOption displayMenu() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                               TASKS MANAGER                       ");
        System.out.println("-------------------------------------------------------------------");
        System.out.print("0. Quit \t\t\t\t\t\t\t");
        System.out.println("1. Add new task");
        System.out.print("2. Add new person \t\t\t\t\t");
        System.out.println("3. Delete person by person ID");
        System.out.print("4. Delete task by task ID\t\t\t");
        System.out.println("5. Update person by person ID");
        System.out.print("6. Update task by task ID\t\t\t");
        System.out.println("7. Display all tasks");
        System.out.print("8. Display all people\t\t\t\t");
        System.out.println("9. Find tasks by person ID");
        System.out.print("10. Find tasks by task ID\t\t\t");
        System.out.println("11. Find tasks by username");
        System.out.print("12. Find tasks by role\t\t\t\t");
        System.out.println("13. Find tasks by Done status");
        System.out.println("14. Find tasks by title");
        System.out.println("------------------------------------------------------------------- ");
        System.out.println("Enter a number: ");
        switch (getInt()) {
            case 1:
                return MenuOption.ADD_TASK;
            case 2:
                return MenuOption.ADD_PERSON;
            case 3:
                return MenuOption.DELETE_PERSON_BY_PERSON_ID;
            case 4:
                return MenuOption.DELETE_TASK_BY_TASK_ID;
            case 5:
                return MenuOption.UPDATE_PERSON_BY_PERSON_ID;
            case 6:
                return MenuOption.UPDATE_TASK_BY_TASK_ID;
            case 7:
                return MenuOption.DISPLAY_ALL_TASKS;
            case 8:
                return MenuOption.DISPLAY_ALL_PEOPLE;
            case 9:
                return MenuOption.FIND_TASKS_BY_PERSON_ID;
            case 10:
                return MenuOption.FIND_TASKS_BY_TASK_ID;
            case 11:
                return MenuOption.FIND_TASKS_BY_USERNAME;
            case 12:
                return MenuOption.FIND_TASKS_BY_ROLE;
            case 13:
                return MenuOption.FIND_TASKS_BY_DONE_STATUS;
            case 14:
                return MenuOption.FIND_TASKS_BY_TITLE;
            default:
                return MenuOption.QUIT;
        }
    }

    public int getInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getPersonIdData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter person ID:");
        int idInput = scanner.nextInt();
        return idInput;
    }

    public int getTaskIdData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task ID:");
        int idInput = scanner.nextInt();
        return idInput;
    }

    public Person getPersonData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Full name:");
        scanner.nextLine();
        String fn =  scanner.nextLine();
        System.out.println("User name:");
        String un =  scanner.nextLine();
        System.out.println("Password:");
        String pw =  scanner.nextLine();
        System.out.println("Choose Role (user/admin):");
        String r =  scanner.nextLine();
        Role role = Role.valueOf(r.toUpperCase());
        Person personInput = new Person(fn, un, pw, role);
        return personInput;

    }
    public Task getTaskData () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Title:");
        scanner.nextLine();
        String title = scanner.nextLine();
        System.out.println("Description:");
        String des = scanner.nextLine();
        System.out.println("Deadline (YYYY-MM-DD):");
        String dl = scanner.nextLine();
        System.out.println("Done status (true/false):");
        String done = scanner.nextLine();
        System.out.println("Task assigned status (true/false):");
        String assigned = scanner.nextLine();
        System.out.println("assigned to person(ID): ");
        Integer assigneeId = scanner.nextInt();
        Task taskInput = new Task(title, des, LocalDate.parse(dl), Boolean.parseBoolean(done), Boolean.parseBoolean(assigned), assigneeId);
        return taskInput;
    }
    public Person updatePersonData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Person Id:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Full name:");
        String fn = scanner.nextLine();
        System.out.println("User name:");
        String un = scanner.nextLine();
        System.out.println("Password:");
        String pw = scanner.nextLine();
        System.out.println("Choose Role (user/admin):");
        String r = scanner.nextLine();
        Role role = Role.valueOf(r.toUpperCase());
        Person personInput = new Person(id, fn, un, pw, role);
        return personInput;
    }

    public Task updateTaskData () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task Id:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Description:");
        String des = scanner.nextLine();
        System.out.println("Deadline (YYYY-MM-DD):");
        String dl = scanner.nextLine();
        System.out.println("Done status (true/false):");
        String done = scanner.nextLine();
        System.out.println("Task assigned status (true/false):");
        String assigned = scanner.nextLine();
        System.out.println("assigned to person(ID): ");
        Integer assigneeId = scanner.nextInt();

        Task taskInput = new Task(id, title, des, LocalDate.parse(dl), Boolean.parseBoolean(done), Boolean.parseBoolean(assigned), assigneeId);
        return taskInput;
    }

    }
