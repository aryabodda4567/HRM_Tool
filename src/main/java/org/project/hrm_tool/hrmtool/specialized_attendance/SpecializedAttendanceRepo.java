package org.project.hrm_tool.hrmtool.specialized_attendance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializedAttendanceRepo extends CrudRepository<SpecializedAttendanceModel,String> {

    @Query("SELECT S FROM SpecializedAttendanceModel  S WHERE  S.employeeId = :employeeId AND " +
            "S.attendanceMonth = :month AND S.attendanceYear = :year")
    SpecializedAttendanceModel searchSpecializedAttendanceModelByEmployeeIdOfMonthAndYear
            (String employeeId, int month, int year);


}
