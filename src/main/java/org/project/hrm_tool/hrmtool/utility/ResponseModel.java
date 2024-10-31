package org.project.hrm_tool.hrmtool.utility;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {

    private int status;
    private String message;
    private  Object data;
    public ResponseModel(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
