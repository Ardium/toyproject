package com.logsjejustone.webapiserver.user.service;

import com.logsjejustone.webapiserver.user.domain.AdminUserPassword;
import com.logsjejustone.webapiserver.user.repository.AdminUserPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
public class AdminUserPasswordService {

    @Autowired
    private AdminUserPasswordRepository adminUserPasswordRepository;

    private final LocalDateTime localDateTime = LocalDateTime.now();

    // CREATE
    public ResponseEntity<AdminUserPassword> InsertAdminUserPw(Map<String, String> adminUserPw) {
        ResponseEntity<AdminUserPassword> responseEntity;
        String employeeNo = adminUserPw.get("employeeNo");

        AdminUserPassword adminUserPwLatest = this.adminUserPasswordRepository.findFirstByEmployeeNoOrderByNoDesc(employeeNo);
        System.out.println("adminUserPwLatest:" + adminUserPwLatest);

        if(adminUserPwLatest == null) {
            System.out.println("[ERROR/InsertAdminUserPw] There is no data of employee(" + employeeNo + ")");
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            AdminUserPassword adminUserPwNew = new AdminUserPassword();

            // Copy/Paste
            adminUserPwNew.setEmployeeNo(employeeNo);
            adminUserPwNew.setPreviousPw2(adminUserPwLatest.getPreviousPw1());
            adminUserPwNew.setPreviousPw1(adminUserPwLatest.getCurrentPw());

            // New data
            adminUserPwNew.setRegisterEmployeeNo(employeeNo);
            adminUserPwNew.setRegisterDatetime(localDateTime);
            adminUserPwNew.setUpdateEmployeeNo(employeeNo);
            adminUserPwNew.setUpdateDatetime(localDateTime);

            adminUserPwNew.setNo(adminUserPwLatest.getNo() + 1);
            adminUserPwNew.setCurrentPw(adminUserPw.get("currentPw"));
            adminUserPwNew.setPwTrialState(adminUserPw.get("pwTrialState"));
            adminUserPwNew.setTemporaryPwState(adminUserPw.get("temporaryPwState"));

            this.adminUserPasswordRepository.save(adminUserPwNew);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }

        return responseEntity;
    }

    // READ
    public AdminUserPassword GetLatestPwHistory(String employeeNo) {
        return this.adminUserPasswordRepository.findFirstByEmployeeNoOrderByNoDesc(employeeNo);
    }
}
