package org.damsi.todoapplicationfullstack.services;

import org.damsi.todoapplicationfullstack.models.ToDo;
import org.damsi.todoapplicationfullstack.models.User;
import org.damsi.todoapplicationfullstack.repositories.ToDoRepository;
import org.damsi.todoapplicationfullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository){
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    // Get the current user
    private User getAutheticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void createToDo(ToDo toDo){
        User user = getAutheticatedUser();
        toDo.setUser(user);
        toDoRepository.save(toDo);
    }

    public List<ToDo> getUserToDos(){
        User user = getAutheticatedUser();
        return toDoRepository.findByUserId(user.getId());
    }

    public void updateToDo(Long id, ToDo newTodo){
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()){
            ToDo toDo = optionalToDo.get();
            User user = getAutheticatedUser();
            toDo.setTitle(newTodo.getTitle());
            toDo.setDescription(newTodo.getDescription());
            toDo.setCompleted(newTodo.isCompleted());
            toDo.setUser(user);
            toDoRepository.save(toDo);
        }
    }

    public void deleteToDoById(Long id){
        toDoRepository.deleteById(id);
    }
}
