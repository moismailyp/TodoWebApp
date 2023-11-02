package com.example.myprojrcts.firstwebapp12.Todo;

import com.example.myprojrcts.firstwebapp12.Security.CreateUserRequest;
import com.example.myprojrcts.firstwebapp12.Security.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {
    final UserService user;

    @GetMapping("/")
    public String showLoginform(@AuthenticationPrincipal(expression = "username")String username, Model model, HttpSession session)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin",isAdmin);
        model.addAttribute("username",username);
        session.setAttribute("username",username);
        session.setAttribute("isAdmin",isAdmin);

        return "Homepage";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            if (error != null) {
                model.addAttribute("error", " password");
            }
            return "login";
        }
        return "redirect:/";
    }


    @GetMapping("/signup")
    public String getSignUpPage(Model model)
    {
        model.addAttribute("signuprequest",new CreateUserRequest());
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(@ModelAttribute("signuprequest")CreateUserRequest signUpRequest, Model model) {
        boolean result= user.createUser(signUpRequest);
        if(!result && user.getFlag()==1) {
            model.addAttribute("error", "password doesnt match");
            return "/signup";
        }else if(!result && user.getFlag()==0){
            model.addAttribute("error","password does not match");
            return "/signup";

        }else {
            return "redirect:/login";
        }
    }
}