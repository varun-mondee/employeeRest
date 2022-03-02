package com.rest.controller;

import com.rest.model.Manager;
import com.rest.model.Employee;
import com.rest.repository.ManagerRepository;
import com.rest.service.LeaveService;
import com.rest.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String saveDetails(Manager manager)
    {
        managerRepository.save(manager);
        return "managerlogin";
    }

    @RequestMapping(value="/managerlogin",method= RequestMethod.POST)
    public String managerLogin(Model model, Manager manager)
    {
        Manager man= managerService.loginByCredentials(manager.getEmail(),manager.getPassword());
        model.addAttribute("manager",man);
        return "managerhome";
    }

    @RequestMapping(value="/myprofile/{id}")
    public String managerProfile(@PathVariable("id") int id, Model model)
    {
        Manager manager=  managerRepository.findById(id).get();
        model.addAttribute("manager",manager);
        return "managerprofile";
    }

    @RequestMapping(value="/myleaves/{id}")
    public String myappointment(@PathVariable("id") int id, Model model)
    {
        Manager manager=  managerRepository.findById(id).get();
        model.addAttribute("manager",manager);
        model.addAttribute("list",leaveService.myLeaves(manager.getDepartment()));
        return "managerleaves";
    }

}
