package org.project.hrm_tool.hrmtool.specialized_attendance;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "specialized_attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecializedAttendanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String monthId;

    @NotEmpty(message = "Employee id is mandatory")
    String employeeId;

    int attendanceYear;

    int attendanceMonth;

    @ElementCollection
    @CollectionTable(name = "attendance_list", joinColumns = @JoinColumn(name = "employee_attendance_id"))
    @Column(name = "attendance_status")
    private ArrayList<String> attendanceStatuses;

    int currentAttendance;

    double currentAttendancePercentage;
    double payPerDay;
    double currentPay;
}

