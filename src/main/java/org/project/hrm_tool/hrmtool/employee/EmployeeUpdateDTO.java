package org.project.hrm_tool.hrmtool.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDTO {
    private String employeeName;
    private String employeeSurname;
    private String employeeEmail;
    private String employeePhone;
    private String employeeAddress;
    private String employeeGender;
    private String employeeDesignation;
    private String employeeDepartment;
    private String employeeJobTitle;
    private String employeeJobDescription;
    private String employeeJobStatus;
    private Float employeeSalary;
    private Date employeeBirthday;
    private Date employeeHireDate;
    private float employeePerDaySalary;

    // Getters and setters
}
