package org.project.hrm_tool.hrmtool.employee;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.hrm_tool.hrmtool.utility.Utility;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeModel {

    @Id
    String employeeId = String.valueOf(Utility.generateRandomNumber(100000,9000000));

    @NotEmpty(message = "Name should not be empty")
    String employeeName;

    @NotEmpty(message = "Surname should not be empty")
    String employeeSurname;

    @NotEmpty(message = "Email should not be empty")
    String employeeEmail;

    @NotEmpty(message = "Phone number should not be empty")
    String employeePhone;

    @NotEmpty(message = "Address should not be empty")
    String employeeAddress;

    @NotEmpty(message = "Gender should not be empty")
    String employeeGender;

    @NotEmpty(message = "Designation should not be empty")
    String employeeDesignation;

    @NotEmpty(message = "Department should not be empty")
    String employeeDepartment;

    @NotEmpty(message = "Job title should not be empty")
    String employeeJobTitle;

    @NotEmpty(message = "Job description should not be empty")
    String employeeJobDescription;

    @NotEmpty(message = "Job status should not be empty")
    String employeeJobStatus;

    float employeeSalary;
    float employeePerDaySalary;
    Date employeeBirthday;
    Date employeeHireDate;
    String employeePassword;

}
