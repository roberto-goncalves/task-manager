package com.task.taskmanager.todo;

import org.springframework.data.annotation.Id;

public class Todo {


    @Id
    public String id;
    public String description;
    public String status;


    public Todo(String id, String description) {
        setId(id);
        setDescription(description);
        setStatus("Pending");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
