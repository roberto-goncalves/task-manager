package com.task.taskmanager.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path="/todo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @PostMapping(path="/", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @PutMapping(path="/",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @GetMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<Todo> selectSpecific(@PathVariable String id) {
        return todoRepository.findById(id);
    }

    @GetMapping(path="/",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Todo> selectAll() {
        return todoRepository.findAll();
    }

    @DeleteMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSpecific(@PathVariable String id) {
        todoRepository.deleteById(id);
    }

    @DeleteMapping(path="/",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAll() {
        todoRepository.deleteAll();
    }

}
