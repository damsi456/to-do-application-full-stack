package org.damsi.todoapplicationfullstack.repositories;

import org.damsi.todoapplicationfullstack.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
