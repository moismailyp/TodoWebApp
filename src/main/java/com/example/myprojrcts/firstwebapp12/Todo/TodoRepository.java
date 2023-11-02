package com.example.myprojrcts.firstwebapp12.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoClass,Integer> {
    public List <TodoClass>findByUsername(String username);


}
