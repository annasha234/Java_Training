package org;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; // Only non-primitive datatypes allowed
    @Column(name = "task")
    private String task;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long Id){
        this.Id = Id;
    }
    public void setTask(String task){
        this.task= task;
    }
    public Long getId(){
        return this.Id;
    }
    public String getTask(){
        return this.task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}