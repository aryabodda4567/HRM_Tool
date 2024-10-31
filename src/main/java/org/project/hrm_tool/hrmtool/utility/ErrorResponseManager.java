package org.project.hrm_tool.hrmtool.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ErrorResponseManager {
    private List<String> errors;

    public ErrorResponseManager() {
        this.errors = new ArrayList<>();
    }

    public void addError(String error) {
        this.errors.add(error);
    }

}
