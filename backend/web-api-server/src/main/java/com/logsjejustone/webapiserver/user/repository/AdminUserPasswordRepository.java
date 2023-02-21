package com.logsjejustone.webapiserver.user.repository;

import com.logsjejustone.webapiserver.user.domain.AdminUserPassword;
import com.logsjejustone.webapiserver.user.domain.AdminUserPasswordKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserPasswordRepository extends JpaRepository<AdminUserPassword, AdminUserPasswordKey> {

    AdminUserPassword findFirstByEmployeeNoOrderByNoDesc(String employeeNo);
}
