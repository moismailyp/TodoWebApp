package com.example.myprojrcts.firstwebapp12.Todo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="TodoList")
public class TodoClass {


    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String description;
    private boolean completed;

}