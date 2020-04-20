package com.soft.srms.controller;

import com.soft.srms.model.User;
import com.soft.srms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showRegisterForm(Model model) {
        if(!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }

        return "registration";
    }

    @PostMapping("")
    public String showRegisterForm(@Valid User user, Errors errors, BindingResult bindingResult) {
        if(errors.hasErrors()) {
            return "registration";
        }
        if(userService.isUsernameTaken(user.getUsername())) {
            FieldError usernameTakenError = new FieldError("user", "username", user.getUsername(), false, null, null, "Username is already taken.");
            bindingResult.addError(usernameTakenError);
            return "registration";
        }
        else {
            userService.createUserAccount(user);
            return "redirect:/login";
        }
    }
}
