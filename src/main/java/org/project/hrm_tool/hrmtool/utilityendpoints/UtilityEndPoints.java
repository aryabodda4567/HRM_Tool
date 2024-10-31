package org.project.hrm_tool.hrmtool.utilityendpoints;


import org.project.hrm_tool.hrmtool.departments.DepartmentService;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hrm/utility/")
public class UtilityEndPoints {

    DepartmentService departmentService;
    UtilityEndPoints(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/get-department-codes-and-names")
    public ResponseEntity<ResponseModel> getDepartmentCodesAndNames() {

         ResponseModel responseModel=  departmentService.getDepartmentNamesAndCodeList();

         return ResponseEntity.ok(responseModel);

    }

}
