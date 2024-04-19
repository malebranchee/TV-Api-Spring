package com.example.lab5.Controllers;

import com.example.lab5.Repositories.Entities.User;
import com.example.lab5.Services.UserService;
import com.example.lab5.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthorizationController {
    private UserService userService;


    //
    // Registration handler
    //
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid UserDto userdto, BindingResult bindingResult, Model model) {

        if (!userdto.getPassword().equals(userdto.getPasswordConfirm())){
            bindingResult.addError(new ObjectError("passwordError", "Passwords do not match"));
        }
        if (userService.existsByUsername(userdto.getUsername())){
            bindingResult.addError(new ObjectError("usernameError", "Username is already in use"));
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(userdto);
        return "redirect:login";
    }
    @PostMapping("/registration-error")
    public String registrationError(@ModelAttribute @Valid UserDto userdto, Model model) {
        model.addAttribute("registrationError", true);
        return "registration";
    }


    //
    // Login handler
    //
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid UserDto userdto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (userService.loadUserByUsername(userdto.getUsername()).getAuthorities().contains("ROLE_ADMIN")){
            return "redirect:/api/admin";
        }
        return "redirect:/api";
    }
    // Login form with error
    @PostMapping("/login-error")
    public String loginError(@ModelAttribute @Valid UserDto userDto, Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
