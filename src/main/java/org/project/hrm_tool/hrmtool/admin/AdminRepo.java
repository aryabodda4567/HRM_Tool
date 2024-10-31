package org.project.hrm_tool.hrmtool.admin;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo  extends CrudRepository<AdminModel,String> {


    @Query("SELECT A FROM AdminModel A WHERE A.adminEmail = :email")
    public AdminModel findUserByEmail(@Param("email") String email);

}

