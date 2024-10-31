package org.project.hrm_tool.hrmtool.employee;


import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hrm/employee/")
public class EmployeeController {

        EmployeeService employeeService;
        EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;    }

        @GetMapping("/login")
    public  ModelAndView login() {
            return new ModelAndView("employee/employee_login");
        }

    @GetMapping("/update-password")
    public  ModelAndView updatePassword() {

        return new ModelAndView("employee/update-password");
    }

    @GetMapping("/home")
    public  ModelAndView home(HttpSession session) {
        if(session.getAttribute("employee_id")==null) {
            return new ModelAndView( "redirect:/hrm/employee/login");
        }else
        { return new ModelAndView("employee/employee_home");}
    }



    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("employee/logout");

    }


    @GetMapping("/view-profile")
    public ModelAndView viewEmployeeDetails(HttpSession session) {
        if(session.getAttribute("employee_id")==null) {
            return new ModelAndView( "redirect:/hrm/employee/login");
        }

        ResponseModel responseModel = employeeService.findEmployeeByEmployeeId(
                session.getAttribute("employee_id").toString());


        ModelAndView modelAndView = new ModelAndView("employee/view-employee");
        modelAndView.addObject("employee", responseModel);
        return modelAndView;

    }







}
