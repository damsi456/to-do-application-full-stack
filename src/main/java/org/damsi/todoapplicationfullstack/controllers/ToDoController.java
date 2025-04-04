package org.damsi.todoapplicationfullstack.controllers;

import org.damsi.todoapplicationfullstack.models.ToDo;
import org.damsi.todoapplicationfullstack.services.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getUserToDos(){
        return toDoService.getUserToDos();
    }

    @PostMapping
    public void createToDo(@RequestBody ToDo toDo){
        toDoService.createToDo(toDo);
    }

    @PutMapping("/{id}")
    public void updateToDo(@PathVariable Long id, @RequestBody ToDo toDo){
        toDoService.updateToDo(id, toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable Long id){
        toDoService.deleteToDoById(id);
    }
}
