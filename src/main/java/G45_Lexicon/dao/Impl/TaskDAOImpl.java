package G45_Lexicon.dao.Impl;

import G45_Lexicon.dao.Impl.sequencer.PersonIdSequencer;
import G45_Lexicon.dao.TaskDAO;
import G45_Lexicon.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Singleton implementation
//step 1: define a static field with the class type (In this scenario, class type is:  PersonDAOImpl)
//step 2: make constructor private
//step 3: define a static method with if condition to check the object is null or no ( getInstance method)
public class TaskDAOImpl implements TaskDAO {
    //fields
    private List<Task> taskList;
    private static TaskDAOImpl instance;

    //constructors
    private TaskDAOImpl() {
        taskList = new ArrayList<>();
    }

    public static TaskDAOImpl getInstance() {
        if (instance == null) instance = new TaskDAOImpl();
        return instance;
    }

    //methods
    @Override
    public Task create(Task task) {
        if (task == null) throw new IllegalArgumentException("Task was null");
        task.setTaskId(PersonIdSequencer.nextId());
        taskList.add(task);
        return task;
    }

    @Override
    public Optional<Task> findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        for (Task task : taskList) {
            if (task.getTaskId().equals(id)) return Optional.of(task);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteByID(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        return taskList.remove(findById(id));
    }

    @Override
    public void update(Task updatedTask) {
        if (updatedTask == null) throw new IllegalArgumentException("updatedTask was null");
        for (Task task : taskList) {
            if (task.getTaskId().equals(updatedTask.getTaskId())) {
                task.setTitle(updatedTask.getTitle());
                task.setAssignee(updatedTask.getAssignee());
                task.setAssigned(updatedTask.isAssigned());
                task.setDescription(updatedTask.getDescription());
                task.setDoneStatus(updatedTask.isDoneStatus());
                task.setDeadLine(updatedTask.getDeadLine());
                break;
            }
        }
    }

    @Override
    public List<Task> findAll() {return new ArrayList<>(taskList);}

    @Override
    public List<Task> findByDoneStatus(boolean doneStatus) {
        List<Task> filteredList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.isDoneStatus() == doneStatus) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    @Override
    public List<Task> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("title was null");
        List<Task> filteredList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTitle().contains(title)) filteredList.add(task);
        }
        return filteredList;
    }


}
