package com.lambdaschool.schoolinthecloud.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usertasks")
public class UserTasks extends Auditable {
    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskid;

    private String description;

    //ManyToOne -> User
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "usertasks",
            allowSetters = true)
    private User user;

    //default constructor for JPA
    public UserTasks() {
    }

    public UserTasks(User user, String description) {
        this.user = user;
        this.description = description;
    }

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
