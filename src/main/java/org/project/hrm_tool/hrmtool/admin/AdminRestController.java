package org.project.hrm_tool.hrmtool.admin;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.project.hrm_tool.hrmtool.utility.ErrorResponseManager;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/hrm/admin")
public class AdminRestController {


    AdminService adminService;

    AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAdmin(@Valid  @RequestBody AdminModel admin, BindingResult bindingResult) {

        // Check for validation errors
        ErrorResponseManager errorResponse =  new  ErrorResponseManager();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                String message = error.getDefaultMessage();
                errorResponse.addError(message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }


        ResponseModel responseModel = adminService.createAdmin(admin);
        if(responseModel.getStatus()==1){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> updateAdmin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        ResponseModel responseModel = adminService.checkAdmin(email, password);

         if(responseModel.getStatus()==1){
             AdminModel adminModel = (AdminModel) responseModel.getData();
             session.setAttribute("admin_name",adminModel.getAdminName());
             session.setAttribute("admin_id",adminModel.getAdminId());
             session.setAttribute("role","ADMIN");

             adminModel.setAdminPassword(null);
             responseModel.setData(adminModel);
             return ResponseEntity.status(HttpStatus.OK).body(responseModel );
         }else{

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseModel);
        }
    }









}
