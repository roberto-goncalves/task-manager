package com.task.taskmanager.todo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository  extends MongoRepository<Todo, String> {
    Todo findByDescription(String description);
    List<Todo> findAll();
}
