package org.project.hrm_tool.hrmtool.departments;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class UpdateDepartmentDTO {





     String departmentCode;

     String departmentName;
     String departmentDescription;

    int employeesCount;
    double departmentBudget;
    String isActive;

    String departmentManagerName;
    int departmentShift;
    Date createdDate;
    Date modifiedDate;


}
