package org.project.hrm_tool.hrmtool.departments;


import jakarta.servlet.http.HttpSession;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/hrm/department/")
public class DepartmentController {

    DepartmentService departmentService;
    DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping()
    public ModelAndView addDepartment(HttpSession   session) {
        if (session.getAttribute("admin_id") == null) {
            return new ModelAndView("redirect:/hrm/admin/login");
        }else {
            return new ModelAndView("departments/add-department");
        }
    }

    @GetMapping("/{department-code}")
    public ModelAndView viewEditDepartment(@PathVariable(name = "department-code") String departmentCode,
                                           HttpSession   session) {
        if (session.getAttribute("admin_id") == null) {
            return new ModelAndView("redirect:/hrm/admin/login");
        }
        ResponseModel responseModel = departmentService.getDepartmentByDepartmentCode(departmentCode);
        ModelAndView modelAndView= new  ModelAndView("departments/view-edit-department");
        modelAndView.addObject("departmentModel", responseModel.getData());
        return modelAndView;
    }

    @GetMapping("/view-all-departments")
    public  ModelAndView viewAllDepartment(HttpSession   session) {
        if (session.getAttribute("admin_id") == null) {
            return new ModelAndView("redirect:/hrm/admin/login");
        }else{
            ResponseModel responseModel = departmentService.getAllDepartments();
            List<DepartmentModel> departmentModelList = (List<DepartmentModel>) responseModel.getData();
             ModelAndView modelAndView= new ModelAndView("departments/view-all-departments");
            modelAndView.addObject("departmentModelList", departmentModelList);
            return modelAndView;
        }
    }




}

