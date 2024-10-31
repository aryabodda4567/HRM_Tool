package org.project.hrm_tool.hrmtool.employee;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.project.hrm_tool.hrmtool.utility.ErrorResponseManager;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hrm/employee/")
public class EmployeeRestController {

    EmployeeService employeeService;
   public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping()
    public ResponseEntity<ResponseModel> addEmployee(@Valid @RequestBody  EmployeeModel employeeModel,
                                                     BindingResult bindingResult,
                                                     HttpSession session) {

        if(session.getAttribute("admin_id")==null) {
            return ResponseEntity.badRequest().body(new
                    ResponseModel(0,"Unauthorized request",employeeModel));
        }

        // Check for validation errors
        ErrorResponseManager errorResponse =  new  ErrorResponseManager();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                String message = error.getDefaultMessage();
                errorResponse.addError(message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseModel(0,"Empty fields",errorResponse));
        }

        //Add employee
        ResponseModel responseModel = employeeService.addEmployee(employeeModel);
        if(responseModel.getStatus()==1){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseModel);
        }
    }


    @DeleteMapping()
    public  ResponseEntity<ResponseModel> deleteEmployee(@RequestParam String employeeId, HttpSession session) {


        if(session.getAttribute("admin_id")==null) {
            return ResponseEntity.badRequest().body(new
                    ResponseModel(0,"Unauthorized request",null));
        }

        if(employeeId.isEmpty()){
            return  ResponseEntity.noContent().build();
        }

        ResponseModel responseModel = employeeService.deleteEmployeeByEmployeeId(employeeId);
        if(responseModel.getStatus()==1){
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
        }
    }

    @PutMapping()
    public  ResponseEntity<ResponseModel> updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDTO,  HttpSession session) {

        if(session.getAttribute("admin_id")==null) {
            return ResponseEntity.badRequest().body(new
                    ResponseModel(0,"Unauthorized request",null));
        }

        ResponseModel responseModel = employeeService.updateEmployeeByEmployeeEmail(
                employeeUpdateDTO.getEmployeeEmail(), employeeUpdateDTO);

        if(responseModel.getStatus()==1){
            responseModel.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
        }

    }

    @PostMapping("/login")
   public  ResponseEntity<ResponseModel> employeeLogin(@RequestParam String employeeEmail, @RequestParam String employeePassword,
                                                       HttpSession session) {
        if(employeeEmail.isEmpty() || employeePassword.isEmpty()){
            return  ResponseEntity.noContent().build();
        }else{
            ResponseModel responseModel = employeeService.employeeLogin(employeeEmail, employeePassword);
            if(responseModel.getStatus()==1){
                session.setAttribute("employee_email",employeeEmail);
                session.setAttribute("employee_id",responseModel.getData());
                session.setAttribute("role","EMPLOYEE");
                return ResponseEntity.status(HttpStatus.OK).body(responseModel);
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseModel);
            }
        }
   }

   @PutMapping("/update_password")
   public  ResponseEntity<ResponseModel> changeEmployeePassword(@RequestParam String employeeEmail,
                                                               @RequestParam String employeeOldPassword,
                                                               @RequestParam String employeeNewPassword,
                                                               HttpSession httpSession){

       if(httpSession.getAttribute("employee_id")==null) {
           return ResponseEntity.badRequest().body(new
                   ResponseModel(0,"Unauthorized request",null));
       }

        if(employeeEmail.isEmpty() || employeeOldPassword.isEmpty() || employeeNewPassword.isEmpty()){
            return  ResponseEntity.noContent().build();
        }else{

            ResponseModel responseModel = employeeService.changeEmployeePassword(employeeEmail, employeeOldPassword,
                    employeeNewPassword);
            if(responseModel.getStatus()==1){
                return ResponseEntity.status(HttpStatus.OK).body(responseModel);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(responseModel);
            }
        }
   }

//
//   @GetMapping("/{employee-email}")
//   public  ResponseEntity<ResponseModel> getEmployee(@PathVariable(name = "employee-email") String employeeEmail, HttpSession session) {
//         if(session.getAttribute("employee_id")==null && session.getAttribute("admin_id")==null) {
//             return ResponseEntity.badRequest().body(new
//                     ResponseModel(0,"Unauthorized request",null));
//         }
//
//         if(employeeEmail.isEmpty()){
//             return  ResponseEntity.noContent().build();
//         }
//
//         ResponseModel responseModel = employeeService.findEmployeeByEmployeeEmail(employeeEmail);
//         if(responseModel.getStatus()==1){
//             return ResponseEntity.status(HttpStatus.OK).body(responseModel);
//         }else{
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
//         }
//   }












}
