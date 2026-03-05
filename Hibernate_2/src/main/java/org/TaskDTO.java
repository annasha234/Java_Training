package org;

public class TaskDTO {
    private long id;
    private String task;
    private String description;
    private Long userId;

    public TaskDTO(){

    }

    public TaskDTO(long id, String task,  String description, Long userId) {
        this.task = task;
        this.id = id;
        this.description = description;
        this.userId=userId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }


    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
