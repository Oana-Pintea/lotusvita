package com.oanapintea.lotusvita.controller;

import com.oanapintea.lotusvita.entities.Credentials;
import com.oanapintea.lotusvita.entities.FilterLotus;
import com.oanapintea.lotusvita.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/home")
    public String home() {
        return "unlogged-home";
    }

    @GetMapping("/home-page/{email}")
    public ModelAndView home(@PathVariable String email) {
        ModelAndView homePage = new ModelAndView("home");
        homePage.addObject("email", email);
        return homePage;
    }


    @GetMapping("/login")
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView("login");
        Credentials credentials = new Credentials();
        modelAndView.addObject("credentials", credentials);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView signUpPage(){
        ModelAndView modelAndView = new ModelAndView("sign-up");
        Credentials credentials = new Credentials();
        modelAndView.addObject("credentials", credentials);
        return modelAndView;
    }


    @PostMapping("/signIn")
    public ModelAndView signIn(@ModelAttribute Credentials credentials){
        if (loginService.check(credentials)) {
            ModelAndView homePage = new ModelAndView("home");
            homePage.addObject("email", credentials.getEmail());
            return homePage;
        }
        else {
            System.out.println("credentiale gresite");
            return new ModelAndView("login");
        }
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@ModelAttribute Credentials credentials){
        if (loginService.save(credentials)) {
            ModelAndView homePage = new ModelAndView("home");
            homePage.addObject("email", credentials.getEmail());
            return homePage;
        }
        else {
            return signUpPage();
        }
    }
}
