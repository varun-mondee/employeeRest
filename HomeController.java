package com.rest.controller;

import com.rest.model.Manager;
import com.rest.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value="/")
    public String homePage()
    {
        return "home";
    }

    @RequestMapping(value="/employeeRegister")
    public String employeeRegisterPage(Model model)
    {
        model.addAttribute("employee",new Employee());
        return "employeeregister";
    }

    @RequestMapping(value="/managerRegister")
    public String managerRegisterPage(Model model)
    {
        model.addAttribute("manager",new Manager());
        return "managerregister";
    }

    @RequestMapping(value="/employeeLogin")
    public String employeeLogin(Model model)
    {
        model.addAttribute("employee",new Employee());
        return "employeelogin";
    }

    @RequestMapping(value="/managerLogin")
    public String managerLogin(Model model)
    {
        model.addAttribute("manager",new Manager());
        return "managerlogin";
    }
}
