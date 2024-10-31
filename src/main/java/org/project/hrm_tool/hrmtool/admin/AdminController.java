package org.project.hrm_tool.hrmtool.admin;

import jakarta.servlet.http.HttpSession;
import org.project.hrm_tool.hrmtool.employee.EmployeeModel;
import org.project.hrm_tool.hrmtool.employee.EmployeeService;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hrm/admin/")
public class AdminController {

    EmployeeService employeeService;
    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public  String login(){
        return "admin/admin_login.html";
    }
    @GetMapping("/home")
    public  String home(HttpSession session){
        if(session.getAttribute("admin_id") == null){
            return "redirect:login";
        }
        return "admin/admin_home.html";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("admin/logout");

    }

    @GetMapping("/add-employee")
    public String addEmployee(HttpSession session) {

        if(session.getAttribute("admin_id")==null) {
            return "redirect:/hrm/admin/login";
        }


        return "admin/add_employee.html";
    }



//    @GetMapping("view-and-edit-employee/{employee-email}")
//    public  ModelAndView   getEmployee(@PathVariable(name = "employee-email") String employeeEmail,
//                                       HttpSession session) {
//        if( session.getAttribute("admin_id")==null) {
//            return new ModelAndView( "redirect:/hrm/admin/login");
//        }
//        ResponseModel responseModel = employeeService.findEmployeeByEmployeeEmail(employeeEmail);
//        ModelAndView modelAndView = new ModelAndView("admin/view-and-edit-employee");
//        modelAndView.addObject("employee", responseModel);
//        return modelAndView;
//
//    }

    @GetMapping("view-and-edit-employee/{employee-id}")
    public  ModelAndView   getEmployee(@PathVariable(name = "employee-id") String employeeId,
                                       HttpSession session) {
        if( session.getAttribute("admin_id")==null) {
            return new ModelAndView( "redirect:/hrm/admin/login");
        }
        ResponseModel responseModel = employeeService.findEmployeeByEmployeeId(employeeId);
        ModelAndView modelAndView = new ModelAndView("admin/view-and-edit-employee");
        modelAndView.addObject("employee", responseModel);
        return modelAndView;

    }

    @GetMapping("/view-all-employees")
    public ModelAndView viewAllEmployees(HttpSession session) {
        if(session.getAttribute("admin_id")==null) {
            return new ModelAndView("redirect:/hrm/admin/login");
        }
        ResponseModel responseModel = employeeService.findAllEmployees();
        if (responseModel.getStatus()==1) {
            ModelAndView modelAndView = new ModelAndView("admin/view-all-employees");
            List<EmployeeModel> employeeModels = (List<EmployeeModel>) responseModel.getData();
            modelAndView.addObject("employeeModels", employeeModels);
            return modelAndView;
        }else{
            return new ModelAndView("redirect:/hrm/admin/login");
        }
    }








}
