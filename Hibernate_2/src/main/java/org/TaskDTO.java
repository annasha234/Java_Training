package org;

public class TaskDTO {
    private Long id;
    private String task;
    private String description;
    private Long userId;//Foreign key
    //Default Constructor
    public TaskDTO(){

    }
    // Constructor with all fields
    public TaskDTO(Long id, String task,String description,Long userId){
        this.id=id;
        this.task= task;
        this.description= description;
        this.userId=userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
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