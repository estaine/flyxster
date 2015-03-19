package com.estaine.flyxster.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by AndreyRykhalsky on 11.02.15.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/error-login", method = RequestMethod.GET)
    public ModelAndView errorLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
         modelAndView.addObject("error", true);
        return modelAndView;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView page403() {
        return new ModelAndView("403");
    }
}
