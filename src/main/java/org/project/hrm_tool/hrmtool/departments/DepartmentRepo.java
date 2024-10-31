package org.project.hrm_tool.hrmtool.departments;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends CrudRepository<DepartmentModel, String> {

   @Query("SELECT D FROM DepartmentModel  D WHERE  D.departmentCode = :departmentCode")
    public  DepartmentModel findDepartmentByDepartmentCode(  String departmentCode);


   @Query("SELECT  D FROM DepartmentModel  D WHERE D.departmentCode = :departmentCode OR " +
           "D.departmentName = :departmentName")
   public  DepartmentModel findDepartmentByDepartmentNameAndDepartmentCode(String departmentName,
                                                                           String departmentCode);

   @Query("SELECT distinct D.departmentCode FROM DepartmentModel AS  D ")
   public List<String> getDepartmentCodes();

   @Query("SELECT distinct D.departmentName FROM  DepartmentModel  AS D")
    public List<String> getDepartmentNames();


}
