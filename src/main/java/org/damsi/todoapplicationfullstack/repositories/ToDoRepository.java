package org.damsi.todoapplicationfullstack.repositories;

import org.damsi.todoapplicationfullstack.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUserId(long userId);
}
