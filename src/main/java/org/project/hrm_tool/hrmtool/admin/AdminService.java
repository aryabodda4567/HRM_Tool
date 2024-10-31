package org.project.hrm_tool.hrmtool.admin;

import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.project.hrm_tool.hrmtool.utility.Utility;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
public class AdminService {

    AdminRepo adminRepo;
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public  ResponseModel createAdmin(AdminModel admin) {

        if(adminRepo.findUserByEmail(admin.getAdminEmail())==null){
            admin.setAdminPassword(Utility.generateHash(admin.getAdminPassword()));
            adminRepo.save(admin);
            admin.setAdminPassword(null);
            return new ResponseModel(1,"Admin Added",admin);
        }else{
            return new ResponseModel(0,"Admin already exits",null);
        }

    }

     public ResponseModel checkAdmin(String adminEmail, String password) {

          AdminModel model= adminRepo.findUserByEmail(adminEmail);
          if(model == null) {
              return new ResponseModel(0,"Username not found",null);
          }else{
              if(Utility.generateHash(password).equals(model.adminPassword)){
                //  model.setAdminPassword(null);
                  return new ResponseModel(1,"Admin Password Correct.",model);
              }else{
                  return new ResponseModel(0,"Admin Password Incorrect",null);
              }
          }

     }

}
