package org.project.hrm_tool.hrmtool.employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo  extends CrudRepository<EmployeeModel,String> {

    @Query("SELECT  E FROM EmployeeModel  AS E WHERE E.employeeEmail = :employeeEmail")
    public  EmployeeModel findByEmployeeEmail(String employeeEmail);


    @Transactional
    @Modifying
    @Query("DELETE FROM EmployeeModel  E WHERE  E.employeeEmail = :employeeEmail")
    public  void deleteByEmployeeEmail(String employeeEmail);


    @Transactional
    @Modifying
    @Query("UPDATE EmployeeModel E SET E.employeeJobStatus = :employeeJobStatus WHERE E.employeeEmail = :employeeEmail")
    public  void updateEmployeeJobStatus(String employeeEmail ,String employeeJobStatus);


    @Query("SELECT E.employeeJobStatus FROM EmployeeModel AS E WHERE E.employeeEmail = :employeeEmail")
    public String findJobStatusByEmployeeEmail(String employeeEmail);
}
