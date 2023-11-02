package com.example.myprojrcts.firstwebapp12.Todo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

public class TodoController {

    private todoService TodoService;
    private TodoRepository todoRepository;

    @Autowired
    public TodoController(todoService TodoService, TodoRepository todoRepository) {
        super();
        this.TodoService = TodoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("todolist")
    public String Todo(ModelMap model) {
        List<TodoClass> todo = todoRepository.findByUsername("admin");
        model.addAttribute("todo", todo);
        return "TodoHomepage";
    }

    @GetMapping("/addTodo")
    public String addtodo(Model model) {
        model.addAttribute("todo", new TodoClass());


        return "addTodo";
    }

    @PostMapping("/saveTodo")
    public String postaddTodo(@ModelAttribute("todo") TodoClass todo, BindingResult result, HttpSession session) {
        String username1 = (String) session.getAttribute("username");
        todoRepository.save(todo);

        return "redirect:/todolist";

    }

    @GetMapping("/")
    public String showLoginform(@AuthenticationPrincipal(expression = "username") String username, Model model, HttpSession session) {
        model.addAttribute("username", username);
        session.setAttribute("username", username);
        return "Homepage";
    }
//    @GetMapping("/login")
//    public String showLoginForm()
//    {
//
//        return "redirect:/";
//    }
//    @GetMapping("/editTodo")
//    public String editTodo(@RequestParam("id") Integer id,Model model)
//    {
//    TodoClass todo=TodoService.findById(id);
//     model.addAttribute("todo",todo);
//
//        return "Edittodo";
//    }
//    @GetMapping("deleteTodo")
//    public String deleteTodo(@RequestParam("id") Integer id) {
//        TodoService.deleteById(id);
//        return "redirect:/todolist";
//    }
//    @PostMapping("/updateTodo")
//
//        public String updateTodo(@RequestParam("id") Integer id,
//                @RequestParam("todo")String todo,
//                @RequestParam("targetdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date targetdate,
//                                 @RequestParam(value="completed",required = false)boolean completed, Model model,HttpSession session ) {
//
//        String username1 = (String) session.getAttribute("username");
//        TodoService.addTodo(username1, todo, completed);
//
//        return "redirect:/todolist";
//
//    }
//
//
//    }
}