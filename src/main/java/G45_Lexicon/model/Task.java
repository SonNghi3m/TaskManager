package G45_Lexicon.model;

import java.time.LocalDate;
import java.util.Objects;

public class Task {

    //fields
    private Integer taskId;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean doneStatus;
    private boolean isAssigned;
    private Person assignee;

    private Integer assigneeId;

    //Constructors
    public Task(){}

    public Task(String title, String description, LocalDate deadLine, boolean doneStatus, boolean isAssigned) {
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDoneStatus(doneStatus);
        setAssigned(isAssigned);
    }
    public Task(String title, String description, LocalDate deadLine, boolean doneStatus, Person assignee) {
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDoneStatus(doneStatus);
        setAssignee(assignee);
    }

    public Task(String title, String description, LocalDate deadLine, boolean doneStatus, boolean isAssigned, Person assignee) {
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDoneStatus(doneStatus);
        setAssigned(isAssigned);
        setAssignee(assignee);
    }

    public Task(String title, String description, LocalDate deadLine, boolean doneStatus, boolean isAssigned, Integer assigneeId) {
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDoneStatus(doneStatus);
        setAssigned(isAssigned);
        setAssigneeId(assigneeId);
    }

    public Task(Integer taskId, String title, String description, LocalDate deadLine, boolean doneStatus, boolean isAssigned, Integer assigneeId) {
        setTaskId(taskId);
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDoneStatus(doneStatus);
        setAssigned(isAssigned);
        setAssigneeId(assigneeId);
    }
    //methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId && doneStatus == task.doneStatus && isAssigned == task.isAssigned && title.equals(task.title) && description.equals(task.description) && deadLine.equals(task.deadLine) && assignee.equals(task.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, title, description, deadLine, doneStatus, isAssigned, assignee);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", Description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", doneStatus=" + doneStatus +
                ", isAssigned=" + isAssigned +
                ", assignee=" + assignee +
                ", assigneeId=" + assigneeId +
                '}';
    }

    //setters & getters

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }
}
