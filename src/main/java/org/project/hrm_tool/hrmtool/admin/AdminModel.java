package org.project.hrm_tool.hrmtool.admin;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admin")
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String adminId;

    @NotEmpty(message = "Name should not be empty")
    String adminName;
    @NotEmpty(message = "password should not be empty")
    String adminPassword;
    @NotEmpty(message = "email should not be empty")
    String adminEmail;
    @NotEmpty(message = "phone number should not be empty")
    String adminPhone;

    public AdminModel(String adminId, String adminName, String adminPassword, String adminEmail, String adminPhone) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
    }
}
