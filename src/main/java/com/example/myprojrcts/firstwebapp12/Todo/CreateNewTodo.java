package com.example.myprojrcts.firstwebapp12.Todo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CreateNewTodo
{

    private String username;
    private String description;
    private boolean completed;
}
