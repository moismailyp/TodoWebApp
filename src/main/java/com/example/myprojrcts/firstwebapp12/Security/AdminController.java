package com.example.myprojrcts.firstwebapp12.Security;

import com.example.myprojrcts.firstwebapp12.Security.User;
import com.example.myprojrcts.firstwebapp12.Security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showAdminPanel(Model model) {
        List<User> users= userRepository.findAll();
        model.addAttribute("users",users);
        return "admin";
    }

    @GetMapping("/admin/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getCreatePage(Model model) {
        model.addAttribute("createrequest",new CreateUserRequest());
        return "CreateUser";
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String create(@ModelAttribute("createrequest")CreateUserRequest createRequest, Model model) {
        boolean result=userService.createUser(createRequest);
        if(!result && userService.getFlag()==1) {
            model.addAttribute("error","Username or Email Already Exists");
            return "/CreateUser";
        }else if(!result && userService.getFlag()==0){
            return "/CreateUser";
        }else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/search")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String searchUsersByUsername(@RequestParam String searchTerm, Model model) {
        List<User>users= userRepository.findByUserName (searchTerm).stream()
                .collect(Collectors.toList());
        if(users.isEmpty())
        {
            return "redirect:/admin";
        }
        model.addAttribute("users",users);
        return "/admin";
    }

    @GetMapping(value={"/admin/delete/{email}","/admin/admin/delete/{email}"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteUser(@PathVariable ("email") String email ) {
//        userRepository.deleteById(id);
        userService.deleteUserByEmail(email);

        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user=userRepository.findById(id) .orElseThrow();
        model.addAttribute("user", user);
        return "edituser";
    }

    @PostMapping("/admin/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateUser(@ModelAttribute("user") User updatedUser) {
        userRepository.save(updatedUser);
        return "redirect:/admin";
    }

}
//http.authorizeRequests()
//        .antMatchers("/admin/**").hasRole("ADMIN")
//        .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
// ...
