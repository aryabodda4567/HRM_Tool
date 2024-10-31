package org.project.hrm_tool.hrmtool.departments;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.project.hrm_tool.hrmtool.utility.ErrorResponseManager;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hrm/departments/")
public class DepartmentRestController {

    DepartmentService departmentService;
     DepartmentRestController(DepartmentService departmentService){
        this.departmentService = departmentService;

}

    @PostMapping()
    public ResponseEntity<ResponseModel> addDepartment(@Valid @RequestBody DepartmentModel department,
                                                       HttpSession session,
                                                       BindingResult bindingResult) {

        if (session.getAttribute("admin_id") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseModel(0, "Unauthorized access", null)
            );
        }


        // Check for validation errors
        ErrorResponseManager errorResponse = new ErrorResponseManager();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                String message = error.getDefaultMessage();
                errorResponse.addError(message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseModel(0, "Empty fields", errorResponse));
        }

        ResponseModel responseModel = departmentService.addDepartment(department);
        if (responseModel.getStatus() == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseModel);
        }
    }

    @PutMapping()
    public ResponseEntity<ResponseModel> updateDepartment(@RequestBody UpdateDepartmentDTO updateDepartmentDTO
    ,HttpSession session){

                if (session.getAttribute("admin_id") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseModel(0, "Unauthorized access", null)
            );
        }

        ResponseModel responseModel = departmentService.updateDepartment( updateDepartmentDTO);
        if(responseModel.getStatus() == 1){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        }else{
            System.out.println(responseModel.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(responseModel);
        }


    }

    @DeleteMapping()
    public ResponseEntity<ResponseModel> deleteDepartment(@RequestParam String departmentCode,
                                                          HttpSession session){
                         if (session.getAttribute("admin_id") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseModel(0, "Unauthorized access", null)
            );
        }

        ResponseModel responseModel = departmentService.deleteDepartment(departmentCode);
        if(responseModel.getStatus() == 1){
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
        }
    }




    }
