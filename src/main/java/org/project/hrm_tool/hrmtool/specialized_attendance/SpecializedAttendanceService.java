package org.project.hrm_tool.hrmtool.specialized_attendance;


import org.project.hrm_tool.hrmtool.utility.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SpecializedAttendanceService {

    SpecializedAttendanceRepo specializedAttendanceRepo;

    SpecializedAttendanceService(SpecializedAttendanceRepo repo) {
        this.specializedAttendanceRepo = repo;
    }


    public ResponseModel getAttendanceByEmployeeIdOfMonthAndYear(String employeeId,int month,int year) {
        SpecializedAttendanceModel specializedAttendanceModel = specializedAttendanceRepo.
                searchSpecializedAttendanceModelByEmployeeIdOfMonthAndYear(employeeId,month,year);

        if (specializedAttendanceModel == null) {
            return  new ResponseModel(0,"Attendance not found",null);
        }else{
                return new ResponseModel(  1,"Attendance found "+"Month: "+month+
                        " Year "+year,specializedAttendanceModel);
        }

    }

    public ResponseModel saveAttendanceByEmployeeIdOfMonthAndYear(String employeeId,int month,int year,String attendance) {
        ResponseModel responseModel = getAttendanceByEmployeeIdOfMonthAndYear(employeeId,month,year);
        if (responseModel.getStatus()==1) {
            SpecializedAttendanceModel specializedAttendanceModel= (SpecializedAttendanceModel) responseModel.getData();
             specializedAttendanceModel.getAttendanceStatuses().add(attendance);
             specializedAttendanceRepo.save(specializedAttendanceModel);
             return new ResponseModel(  0,"Attendance saved",specializedAttendanceModel);
        }else{
            return new ResponseModel(0,"Attendance not marked",null);
        }


    }


}