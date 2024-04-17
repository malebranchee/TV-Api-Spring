package com.example.lab5.Controllers;

import com.example.lab5.Repositories.Entities.User;
import com.example.lab5.Services.UserService;
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

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }
 // ДОДЕЛАТЬ ТУТ ВАЛИДАЦИЮ
    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid User userForm, BindingResult bindingResult, Model model) {

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            bindingResult.addError(new ObjectError("passwordError", "Passwords do not match"));
        }
        if (userService.existsByUsername(userForm.getUsername())){
            bindingResult.addError(new ObjectError("usernameError", "Username is already in use"));
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(userForm);
        return "redirect:/api";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    // ДОДЕЛАТЬ ТУТ ВАЛИДАЦИЮ
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return "login";
        }

        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Нет пользователя с таким именем");
            return "login";
        }
        if (!userService.loadUserByUsername(user.getUsername()).getPassword().equals(user.getPassword())){
            model.addAttribute("passwordError", "Неверный пароль!");
            return "login";
        }
        if (userService.loadUserByUsername(user.getUsername()).getAuthorities().contains("ROLE_ADMIN")){
            return "redirect:/api/admin";
        }
        return "redirect:/api";
    }
}
