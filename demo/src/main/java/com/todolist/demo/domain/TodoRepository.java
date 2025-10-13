package com.todolist.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long id);

    void deleteByUserAndIsCompleted(User currentUser, boolean isCompleted);
}
