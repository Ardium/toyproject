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
        String currentPw = adminUserPw.get("currentPw");

        AdminUserPassword adminUserPwLatest = this.adminUserPasswordRepository.findFirstByEmployeeNoOrderByNoDesc(employeeNo);
        System.out.println("adminUserPwLatest:" + adminUserPwLatest);

        String latestCurrentPw = (adminUserPwLatest == null) ? "" : adminUserPwLatest.getCurrentPw();
        String latestPreviousPw1 = (adminUserPwLatest == null) ? "" : adminUserPwLatest.getPreviousPw1();
        String latestPreviousPw2 = (adminUserPwLatest == null) ? "" : adminUserPwLatest.getPreviousPw2();
        Integer latestNo = (adminUserPwLatest == null) ? 0 : adminUserPwLatest.getNo();

        if(currentPw.equals(latestCurrentPw) || currentPw.equals(latestPreviousPw1) || currentPw.equals(latestPreviousPw2)) {
            System.out.println("[ERROR/InsertAdminUserPw] 이전에 사용한 비밀번호로 교체할 수 없습니다.");
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else {
            AdminUserPassword adminUserPwNew = new AdminUserPassword();

            // Copy/Paste
            adminUserPwNew.setEmployeeNo(employeeNo);
            adminUserPwNew.setPreviousPw2(latestPreviousPw1);
            adminUserPwNew.setPreviousPw1(latestCurrentPw);

            // New data
            adminUserPwNew.setRegisterEmployeeNo(adminUserPw.get("optEmployeeNo"));
            adminUserPwNew.setRegisterDatetime(localDateTime);
            adminUserPwNew.setUpdateEmployeeNo(adminUserPw.get("optEmployeeNo"));
            adminUserPwNew.setUpdateDatetime(localDateTime);

            adminUserPwNew.setNo(latestNo + 1);
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
