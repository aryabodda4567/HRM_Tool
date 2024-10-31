package org.project.hrm_tool.hrmtool.departments;


import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    DepartmentRepo departmentRepo;
    DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public ResponseModel addDepartment(DepartmentModel department) {
        String departmentName = department.getDepartmentName();
        String departmentCode = department.getDepartmentCode();
        DepartmentModel departmentModel = departmentRepo.findDepartmentByDepartmentNameAndDepartmentCode(
                departmentName, departmentCode
        );
        if (departmentModel == null) {
            departmentRepo.save(department);
            return new ResponseModel(1,"Department Created",null);
        }else{
            return  new ResponseModel(0,"Department Already Exists",null);
        }

    }

    public ResponseModel  getAllDepartments() {
        Iterable<DepartmentModel> departments = departmentRepo.findAll();
        ArrayList<DepartmentModel> departmentModels = new ArrayList<>();
        for (DepartmentModel department : departments) {
            departmentModels.add(department);
        }

        if (!departmentModels.isEmpty()) {
            return new ResponseModel(1,"Department List",departmentModels);
        }else{
            return new ResponseModel(0,"No Departments Found",null);
        }
    }


    public ResponseModel updateDepartment ( UpdateDepartmentDTO updateDepartmentDTO) {
        DepartmentModel departmentModel = departmentRepo.findDepartmentByDepartmentCode(updateDepartmentDTO.getDepartmentCode());

        if (departmentModel != null) {

            departmentModel.setDepartmentName(updateDepartmentDTO.getDepartmentName());
            departmentModel.setDepartmentDescription(updateDepartmentDTO.getDepartmentDescription());
            departmentModel.setDepartmentBudget(updateDepartmentDTO.getDepartmentBudget());
            departmentModel.setIsActive(updateDepartmentDTO.getIsActive());
            departmentModel.setDepartmentManagerName(updateDepartmentDTO.getDepartmentManagerName());
            departmentModel.setDepartmentShift(updateDepartmentDTO.getDepartmentShift());

            departmentRepo.save(departmentModel);
            // Save updated department
            return new ResponseModel(1,"Department Updated",null);
        } else {
            return new ResponseModel(0,"Department Not Found",null);
        }
    }

    public  ResponseModel deleteDepartment(String departmentCode) {
        DepartmentModel departmentModel = departmentRepo.findDepartmentByDepartmentCode(departmentCode);
        if (departmentModel != null) {
            departmentRepo.deleteById(departmentModel.getDepartmentId());
            return new ResponseModel(1,"Department Deleted",null);
        }else{
            return new ResponseModel(0,"Department Not Found",null);
        }
    }

    public  ResponseModel getDepartmentNamesAndCodeList() {
        List<String> departmentCodesList = departmentRepo.getDepartmentCodes();
        List<String> departmentNamesList = departmentRepo.getDepartmentNames();
         List<List<String>>  resultList = new ArrayList<>();

         System.out.println(departmentCodesList.size());
         System.out.println(departmentNamesList.size());
         resultList.add(departmentCodesList);
         resultList.add(departmentNamesList);

        return new ResponseModel(1,"Department List",resultList);
     }

     public  ResponseModel getDepartmentByDepartmentCode(String departmentCode) {
        DepartmentModel departmentModel = departmentRepo.findDepartmentByDepartmentCode(departmentCode);
        if (departmentModel != null) {
            return new ResponseModel(1,"Department Found",departmentModel);
        }else
        {
            return new ResponseModel(0,"Department Not Found",null);
        }
     }


}


