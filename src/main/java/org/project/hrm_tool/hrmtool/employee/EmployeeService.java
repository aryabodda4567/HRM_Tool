package org.project.hrm_tool.hrmtool.employee;


import org.project.hrm_tool.hrmtool.utility.Constants;
import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.project.hrm_tool.hrmtool.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements Constants {

    private  EmployeeRepo employeeRepo;
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public ResponseModel addEmployee(EmployeeModel employeeModel) {

        if(employeeRepo.findByEmployeeEmail(employeeModel.getEmployeeEmail()) != null){
            return  new ResponseModel(0,"Employee already exist",employeeModel);
        }else{
            employeeModel.setEmployeePassword(Utility.generateHash("password123"));//Employee should change password
            employeeRepo.save(employeeModel);
            return new ResponseModel(1,"Employee added",employeeModel);
        }
    }

    public ResponseModel findEmployeeByEmployeeEmail(String employeeEmail) {
        EmployeeModel employeeModel=  employeeRepo.findByEmployeeEmail(employeeEmail);
        if(employeeModel == null){
            return new ResponseModel(0,"Employee not found",null);
        }else{
            employeeModel.setEmployeePassword(null);
            return new ResponseModel(1,"Employee found",employeeModel);
        }
    }

    public ResponseModel findEmployeeByEmployeeId(String employeeId) {
        Optional<EmployeeModel> employeeModelOptional=  employeeRepo.findById(employeeId);
        if(employeeModelOptional.isPresent()){
            EmployeeModel employeeModel= employeeModelOptional.get();
            employeeModel.setEmployeePassword(null);
            return new ResponseModel(1,"Employee found",employeeModel);
        }
        else{
            return new ResponseModel(0,"Employee not found",null);
        }
     }

    public ResponseModel deleteEmployeeByEmployeeId(String employeeId) {
        if(findEmployeeByEmployeeId(employeeId).getStatus()!=1){
            return new ResponseModel(0,"Employee not found",null);
        }else{
            employeeRepo.deleteById(employeeId);
            return new ResponseModel(1,"Employee deleted",null);
        }
    }



    public ResponseModel deleteEmployeeByEmployeeEmail(String employeeEmail) {
        if(employeeRepo.findByEmployeeEmail(employeeEmail) == null){
            return new ResponseModel(0,"Employee not found",null);
        }else{
            employeeRepo.deleteByEmployeeEmail(employeeEmail);
            return new ResponseModel(1,"Employee deleted",null);
        }
    }

    public ResponseModel updateEmployeeByEmployeeEmail(String employeeEmail, EmployeeUpdateDTO updateDTO) {
        EmployeeModel currentEmployee = employeeRepo.findByEmployeeEmail(employeeEmail);
        if(currentEmployee != null){

            if(updateDTO.getEmployeeName() != null){
                currentEmployee.setEmployeeName(updateDTO.getEmployeeName());
            }

            if(updateDTO.getEmployeeSurname() != null){
                currentEmployee.setEmployeeSurname(updateDTO.getEmployeeSurname());
            }

            if(updateDTO.getEmployeeEmail() != null){
                currentEmployee.setEmployeeEmail(updateDTO.getEmployeeEmail());
            }

            if(updateDTO.getEmployeePhone() != null){
                currentEmployee.setEmployeePhone(updateDTO.getEmployeePhone());
            }

            if(updateDTO.getEmployeeAddress() != null){
                currentEmployee.setEmployeeAddress(updateDTO.getEmployeeAddress());
            }

            if(updateDTO.getEmployeeGender() != null){
                currentEmployee.setEmployeeGender(updateDTO.getEmployeeGender());
            }

            if(updateDTO.getEmployeeDesignation() != null){
                currentEmployee.setEmployeeDesignation(updateDTO.getEmployeeDesignation());
            }

            if(updateDTO.getEmployeeDepartment() != null){
                currentEmployee.setEmployeeDepartment(updateDTO.getEmployeeDepartment());
            }

            if(updateDTO.getEmployeeJobTitle() != null){
                currentEmployee.setEmployeeJobTitle(updateDTO.getEmployeeJobTitle());
            }

            if(updateDTO.getEmployeeJobDescription() != null){
                currentEmployee.setEmployeeJobDescription(updateDTO.getEmployeeJobDescription());
            }

            if(updateDTO.getEmployeeJobStatus() != null){
                currentEmployee.setEmployeeJobStatus(updateDTO.getEmployeeJobStatus());
            }

            if(updateDTO.getEmployeeSalary() != 0.0f){  // You can check if salary is 0.0f to avoid updating with a default value
                currentEmployee.setEmployeeSalary(updateDTO.getEmployeeSalary());
            }

            if(updateDTO.getEmployeeBirthday() != null){
                currentEmployee.setEmployeeBirthday(updateDTO.getEmployeeBirthday());
            }

            if(updateDTO.getEmployeeHireDate() != null){
                currentEmployee.setEmployeeHireDate(updateDTO.getEmployeeHireDate());
            }
            if(updateDTO.getEmployeePerDaySalary() !=0.0f){
                currentEmployee.setEmployeePerDaySalary(updateDTO.getEmployeePerDaySalary());
            }

            employeeRepo.save(currentEmployee);
            return new ResponseModel(1,"Employee updated",currentEmployee);
        }else{
            return new ResponseModel(0,"Employee not found",null);
        }
    }

    public  ResponseModel employeeLogin(String employeeEmail, String employeePassword) {
        EmployeeModel currentEmployee = employeeRepo.findByEmployeeEmail(employeeEmail);
        if(currentEmployee != null){
            if(currentEmployee.getEmployeePassword().equals(Utility.generateHash(employeePassword))){
                return new ResponseModel(1,"Employee logged in",currentEmployee.getEmployeeId());
            }else{
                return new ResponseModel(0,"Wrong Password",null);
            }
        }else{
            return new ResponseModel(0,"Email not found",null);
        }
    }

    public ResponseModel changeEmployeePassword(String employeeEmail, String employeeOldPassword,
                                                String employeeNewPassword) {
        EmployeeModel currentEmployee = employeeRepo.findByEmployeeEmail(employeeEmail);
        if(currentEmployee != null){
            if(currentEmployee.getEmployeePassword().equals(Utility.generateHash(employeeOldPassword))){
                currentEmployee.setEmployeePassword(Utility.generateHash(employeeNewPassword));
                employeeRepo.save(currentEmployee);
                return new ResponseModel(1,"Employee password changed",null);
            }else{
                return new ResponseModel(0,"Wrong Password",null);
            }
        }else{
            return new ResponseModel(0,"Email not found",null);
        }
    }

    public  ResponseModel findAllEmployees() {
        Iterable<EmployeeModel> optionalList = employeeRepo.findAll();
        List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
        for (EmployeeModel employeeModel : optionalList) {
            employeeList.add(employeeModel);

        }
        return new ResponseModel(1,"All employees",employeeList);
    }





}
