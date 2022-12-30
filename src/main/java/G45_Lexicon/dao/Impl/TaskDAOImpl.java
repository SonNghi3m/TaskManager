package G45_Lexicon.dao.Impl;

import G45_Lexicon.DBConnectionException;
import G45_Lexicon.MySQLConnection;
import G45_Lexicon.dao.Impl.sequencer.PersonIdSequencer;
import G45_Lexicon.dao.Impl.sequencer.TaskIdSequencer;
import G45_Lexicon.dao.TaskDAO;
import G45_Lexicon.model.Person;
import G45_Lexicon.model.Role;
import G45_Lexicon.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAOImpl implements TaskDAO {

     @Override
    public Task create(Task task) {
         if (task == null) throw new IllegalArgumentException("Task was null");
        String query = "insert into task(title, description, deadline, doneStatus, isAssigned, personId) values (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
                ) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getDeadLine()));
            preparedStatement.setBoolean(4, task.isDoneStatus());
            preparedStatement.setBoolean(5, task.isAssigned());
            preparedStatement.setInt(6, task.getAssigneeId());
            preparedStatement.executeUpdate();
            Integer taskId = null;
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                  taskId = resultSet.getInt(1);
                  task.setTaskId(taskId);
                }
            }
            if (taskId == null) throw new RuntimeException("getGeneratedKeys value for address query was null");
            System.out.println("Task Id: " + task.getTaskId() + " was added!");
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
         System.out.println(task);
         return task;
    }

    @Override
    public boolean deleteByID(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        String query = "delete from task where taskId = ?";
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Task ID: " + id + " was deleted!");

        } catch(DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Task update(Task task) {
        String query = "update task set title = ?, description = ?, deadline = ?, doneStatus = ?, isAssigned = ?, personId = ? where taskId = ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getDeadLine()));
            preparedStatement.setBoolean(4, task.isDoneStatus());
            preparedStatement.setBoolean(5, task.isAssigned());
            preparedStatement.setInt(6, task.getAssigneeId());
            preparedStatement.setInt(7,task.getTaskId());
            preparedStatement.executeUpdate();
            System.out.println("Task Id: " + task.getTaskId() + " was updated!");

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        List<Task> list = new ArrayList<>();
        String query = "select * from task";
        try (
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("taskId"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                task.setAssigned(resultSet.getBoolean("isAssigned"));
                task.setAssigneeId(resultSet.getInt("personId"));

                list.add(task);
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public Task findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        String query = "select * from task where taskId = ?";
        Task task = new Task();
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    task.setTaskId(resultSet.getInt("taskId"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                    task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                    task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                    task.setAssigned(resultSet.getBoolean("isAssigned"));
                    task.setAssigneeId(resultSet.getInt("personId"));
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(task);
        return task;
    }
    @Override
    public List<Task> findByPersonId(Integer personId) {
        List<Task> filteredList = new ArrayList<>();
        String query = "select * from task where personId = ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskId(resultSet.getInt("taskId"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                    task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                    task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                    task.setAssigned(resultSet.getBoolean("isAssigned"));
                    task.setAssigneeId(resultSet.getInt("personId"));
                    filteredList.add(task);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }
    @Override
    public List<Task> findByUsername(String username) {
        List<Task> filteredList = new ArrayList<>();
        String query = "select * from task left join person on task.personId = person.personId where person.username = ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskId(resultSet.getInt("taskId"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                    task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                    task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                    task.setAssigned(resultSet.getBoolean("isAssigned"));
                    task.setAssigneeId(resultSet.getInt("personId"));
                    filteredList.add(task);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }
    @Override
    public List<Task> findByRole(String role) {
        List<Task> filteredList = new ArrayList<>();
        String query = "select * from task left join person on task.personId = person.personId where person.role = ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, role);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskId(resultSet.getInt("taskId"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                    task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                    task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                    task.setAssigned(resultSet.getBoolean("isAssigned"));
                    task.setAssigneeId(resultSet.getInt("personId"));
                    filteredList.add(task);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }
    @Override
    public List<Task> findByDoneStatus(boolean doneStatus) {
        List<Task> filteredList = new ArrayList<>();
        String query = "select * from task where doneStatus = ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setBoolean(1, doneStatus);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskId(resultSet.getInt("taskId"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                    task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                    task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                    task.setAssigned(resultSet.getBoolean("isAssigned"));
                    task.setAssigneeId(resultSet.getInt("personId"));
                    filteredList.add(task);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }

    @Override
    public List<Task> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("name was null");
        List<Task> filteredList = new ArrayList<>();
        String query = "select * from task where title like ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "%" + title + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskId(resultSet.getInt("taskId"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDescription(resultSet.getString("description"));
                    task.setDeadLine(resultSet.getDate("deadline").toLocalDate());
                    task.setDoneStatus(resultSet.getBoolean("doneStatus"));
                    task.setAssigned(resultSet.getBoolean("isAssigned"));
                    task.setAssigneeId(resultSet.getInt("personId"));

                    filteredList.add(task);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }

}
