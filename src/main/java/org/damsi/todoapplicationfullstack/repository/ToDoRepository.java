package org.damsi.todoapplicationfullstack.repository;

import org.damsi.todoapplicationfullstack.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
