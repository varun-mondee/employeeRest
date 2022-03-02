package com.rest.controller;

import com.rest.model.Leave;
import com.rest.model.Employee;
import com.rest.repository.LeaveRepository;
import com.rest.repository.EmployeeRepository;
import com.rest.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.List;

@Controller
public class LeaveController {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value="/employee/{id}/leave")
    public String bookLeave(Model model, @PathVariable int id)
    {
        model.addAttribute("id",id);
        model.addAttribute("employee",employeeRepository.findById(id).get());
        model.addAttribute("leave", new Leave());
        return "requestleaves";
    }
    @RequestMapping(value="/employee/{id}/leave/save",method= RequestMethod.POST)
    public String saveLeave(Leave leave,@PathVariable int id,Model model)
    {
        Employee employee=employeeRepository.findById(id).get();
        leave.setEmployeeId(employee);
        leaveRepository.save(leave);
        model.addAttribute("employee",employee);
        return "employeehome";
    }
    @RequestMapping(value="/employee/{id}/myleaves",method= RequestMethod.GET)
    public String myLeave(Leave leave, @PathVariable int id, Model model)
    {
        Employee employee=employeeRepository.findById(id).get();
        model.addAttribute("leave",leaveService.getLeaves(employee));
        model.addAttribute("employee",employee);
        return "myleaves";
    }

    @RequestMapping(value="/accept/{id}",method= RequestMethod.GET)
    public String acceptLeave(Leave leave, @PathVariable int id, Model model) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
    	leaveService.acceptLeave(id);
    	Employee employee=employeeRepository.findById(id).get();
        model.addAttribute("leaves",leaveService.getLeaves(employee));
        model.addAttribute("employee",employee);
        return "myleaves";
    }

    @RequestMapping(value="/reject/{id}",method= RequestMethod.GET)
    public String rejectLeave(Leave leave, @PathVariable int id, Model model) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        leaveService.rejectLeave(id);
        Employee employee=employeeRepository.findById(id).get();
        model.addAttribute("leaves",leaveService.getLeaves(employee));
        model.addAttribute("employee",employee);
        return "myleaves";
       
    }

}
