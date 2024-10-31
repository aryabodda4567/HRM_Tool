package org.project.hrm_tool.hrmtool.departments;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String departmentId;

    @NotEmpty(message = "Department code should not be empty")
    String departmentCode;

    @NotEmpty(message = "Department name should not be empty")
    String departmentName;
    @NotEmpty(message = "Department description should not be empty")
    String departmentDescription;

    int employeesCount;
    double departmentBudget;
    String isActive;

    @NotEmpty(message = "Department manager name should not be empty")
    String departmentManagerName;
    int departmentShift;
    Date createdDate;
    Date modifiedDate;



}
