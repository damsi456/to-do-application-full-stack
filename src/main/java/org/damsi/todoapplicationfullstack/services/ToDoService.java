package org.damsi.todoapplicationfullstack.services;

import org.damsi.todoapplicationfullstack.models.ToDo;
import org.damsi.todoapplicationfullstack.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos(){
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getToDoById(Long id){
        return toDoRepository.findById(id);
    }

    public ToDo createToDo(ToDo toDo){
        return toDoRepository.save(toDo);
    }

    public ToDo updateToDo(Long id, ToDo newTodo) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if(optionalToDo.isPresent()){
            ToDo toDo = optionalToDo.get();
            toDo.setTitle(newTodo.getTitle());
            toDo.setCompleted(newTodo.isCompleted());
            toDo.setDescription(newTodo.getDescription());
            return toDoRepository.save(toDo);
        } else {
            return null;
        }
    }

    public void deleteToDoById(Long id){
        toDoRepository.deleteById(id);
    }
}
