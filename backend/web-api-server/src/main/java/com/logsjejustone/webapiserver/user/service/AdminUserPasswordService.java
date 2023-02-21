package com.logsjejustone.webapiserver.user.service;

import com.logsjejustone.webapiserver.user.domain.AdminUserPassword;
import com.logsjejustone.webapiserver.user.repository.AdminUserPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminUserPasswordService {

    @Autowired
    private AdminUserPasswordRepository adminUserPasswordRepository;

    // READ
    public AdminUserPassword GetLatestPwHistory(String employeeNo) {
        AdminUserPassword adminUserPw = this.adminUserPasswordRepository.findFirstByEmployeeNoOrderByNoDesc(employeeNo);
        System.out.println(employeeNo + " : " + adminUserPw);
        return adminUserPw;
    }
}
