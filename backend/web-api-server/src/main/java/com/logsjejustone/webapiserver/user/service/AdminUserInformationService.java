package com.logsjejustone.webapiserver.user.service;

import com.logsjejustone.webapiserver.user.domain.AdminUserInformation;
import com.logsjejustone.webapiserver.user.repository.AdminUserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminUserInformationService {

    @Autowired
    private AdminUserInformationRepository adminUserInformationRepository;

    private final LocalDateTime localDateTime = LocalDateTime.now();

    // CREATE
    public ResponseEntity<AdminUserInformation> AddAdminUser(Map<String, String> employee) {
        ResponseEntity<AdminUserInformation> responseEntity;

        String employeeNo = employee.get("employeeNo");
        Optional<AdminUserInformation> optAdminUserInfo = this.adminUserInformationRepository.findById(employeeNo);

        if(optAdminUserInfo.isPresent()) {
            System.out.println("[ERROR/AddAdminUser] The employee No(" + employeeNo + ") already exists.");

            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else {
            AdminUserInformation adminUserInformation = new AdminUserInformation();

            adminUserInformation.setEmployeeNo(employeeNo);

            adminUserInformation.setRegisterEmployeeNo(employee.get("registerEmployeeNo"));
            adminUserInformation.setRegisterDatetime(localDateTime);
            adminUserInformation.setUpdateEmployeeNo(employee.get("updateEmployeeNo"));
            adminUserInformation.setUpdateDatetime(localDateTime);

            adminUserInformation.setEmployeeCompany(employee.get("employeeCompany"));
            adminUserInformation.setEmployeeDivision(employee.get("employeeDivision"));
            adminUserInformation.setEmployeeTeam(employee.get("employeeTeam"));
            adminUserInformation.setEmployeePosition(employee.get("employeePosition"));
            adminUserInformation.setEmployeePhone(employee.get("employeePhone"));
            adminUserInformation.setEmployeeEmail(employee.get("employeeEmail"));
            adminUserInformation.setEmployeePw(employee.get("employeePw"));
            adminUserInformation.setEmployeeNameEng(employee.get("employeeNameEng"));
            adminUserInformation.setEmployeeName(employee.get("employeeName"));
            adminUserInformation.setUsageExpDate(employee.get("usageExpDate"));

            // 사용자 신규 추가시 임시 비밀번호를 지정하므로, 비밀번호 변경 기한을 7일 뒤로 설정
            String strPwExpDate = localDateTime.plusDays(7).toLocalDate().toString().replaceAll("-", "");
            adminUserInformation.setEmployeePwExpDate(strPwExpDate);

            this.adminUserInformationRepository.save(adminUserInformation);

            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }
        return responseEntity;
    }

    // READ
    public List<AdminUserInformation> GetAllAdminUsers() {
        return this.adminUserInformationRepository.findAll();
    }

    public List<AdminUserInformation> GetAvailableAdminUsers() {
        String usageExpDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("[AdminUserInformation:GetAvailableAdminUsers] Search Condition(usageExpDate):" + usageExpDate);
        return this.adminUserInformationRepository.findByUsageExpDateGreaterThanEqual(usageExpDate);
    }

    public AdminUserInformation GetSpecificAdminUser(String employeeNo) {
        AdminUserInformation adminUserInformation;
        Optional<AdminUserInformation> optAdminUserInfo = this.adminUserInformationRepository.findById(employeeNo);
        if(optAdminUserInfo.isEmpty()) {
            System.out.println("[INFO/UpdateAdminUserPw] The employee No(" + employeeNo + ") does NOT exist.");
            adminUserInformation = null;
        }
        else {
            adminUserInformation = optAdminUserInfo.get();
        }

        return adminUserInformation;
    }

    // UPDATE
    public ResponseEntity<AdminUserInformation> UpdateAdminUserPw(Map<String, String> employee) {
        ResponseEntity<AdminUserInformation> responseEntity;
        String employeeNo = employee.get("employeeNo");

        Optional<AdminUserInformation> optAdminUserInfo = this.adminUserInformationRepository.findById(employeeNo);
        if(optAdminUserInfo.isEmpty()) {
            System.out.println("[ERROR/UpdateAdminUserPw] The employee No(" + employeeNo + ") does NOT exist.");

            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            AdminUserInformation afterAdminUserInfo = optAdminUserInfo.get();

            afterAdminUserInfo.setUpdateDatetime(localDateTime);
            afterAdminUserInfo.setUpdateEmployeeNo(employee.get("updateEmployeeNo"));

            afterAdminUserInfo.setEmployeePw(employee.get("employeePw"));
            int pwExpDate = employee.get("temporaryPwState").equals("Y") ? 7 : 60;
            afterAdminUserInfo.setEmployeePwExpDate(localDateTime.plusDays(pwExpDate).format(DateTimeFormatter.ofPattern("yyyyMMdd")));

            responseEntity = new ResponseEntity<>(HttpStatus.OK);

            this.adminUserInformationRepository.save(afterAdminUserInfo);
        }

        return responseEntity;
    }

    public ResponseEntity<AdminUserInformation> UpdateAdminUserInformation(AdminUserInformation adminUserInformation,
                                                                           String targetEmpNo) {
        ResponseEntity<AdminUserInformation> responseEntity;
        Optional<AdminUserInformation> optAdminUserInfo = this.adminUserInformationRepository.findById(targetEmpNo);

        if(optAdminUserInfo.isEmpty()) {
            System.out.println("[ERROR/UpdateAdminUserInformation] The employee No(" + adminUserInformation.getEmployeeNo() + ") does NOT exist.");

            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            AdminUserInformation afterAdminUserInfo = optAdminUserInfo.get();
            System.out.println("[UpdateAdminUserInformation] afterAdminUserInfo: " + afterAdminUserInfo);

            afterAdminUserInfo.setUpdateDatetime(localDateTime);
            afterAdminUserInfo.setUpdateEmployeeNo(adminUserInformation.getUpdateEmployeeNo());

            // 사용자가 변경하지 못하는 데이터
//            afterAdminUserInfo.setEmployeeName(adminUserInformation.getEmployeeName());
//            afterAdminUserInfo.setEmployeeCompany(adminUserInformation.getEmployeeCompany());

            // 사용자가 변경 가능한 데이터
            // - ROLE:1 이상
            afterAdminUserInfo.setEmployeeNameEng(adminUserInformation.getEmployeeNameEng());
            afterAdminUserInfo.setEmployeeDivision(adminUserInformation.getEmployeeDivision());
            afterAdminUserInfo.setEmployeeTeam(adminUserInformation.getEmployeeTeam());
            afterAdminUserInfo.setEmployeePosition(adminUserInformation.getEmployeePosition());
            afterAdminUserInfo.setEmployeeEmail(adminUserInformation.getEmployeeEmail());
            afterAdminUserInfo.setEmployeePhone(adminUserInformation.getEmployeePhone());

//            비밀번호의 경우 별도의 함수(UpdateAdminUserPw)에서 처리함

            // - ROLE:6
            afterAdminUserInfo.setUsageExpDate(adminUserInformation.getUsageExpDate());

            this.adminUserInformationRepository.save(afterAdminUserInfo);

            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }
        return responseEntity;
    }

    // DELETE
    public ResponseEntity<AdminUserInformation> DeleteAdminUser(AdminUserInformation adminUserInformation,
                                                                String targetEmpNo) {
        // * There is no action for deletion. Just update show/hide flag.
        ResponseEntity<AdminUserInformation> responseEntity;
        Optional<AdminUserInformation> optAdminUserInfo = this.adminUserInformationRepository.findById(targetEmpNo);

        if (optAdminUserInfo.isEmpty()) {
            System.out.println("[ERROR/DeleteAdminUser] The employee No(" + targetEmpNo + ") does NOT exist.");

            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AdminUserInformation afterAdminUserInfo = optAdminUserInfo.get();
            System.out.println("[DeleteAdminUser] afterAdminUserInfo: " + afterAdminUserInfo);

            afterAdminUserInfo.setUpdateDatetime(localDateTime);
            afterAdminUserInfo.setUpdateEmployeeNo(adminUserInformation.getUpdateEmployeeNo());

            // 관리자(ROLE:6)가 변경 가능한 데이터
            afterAdminUserInfo.setUsageExpDate(adminUserInformation.getUsageExpDate());

            this.adminUserInformationRepository.save(afterAdminUserInfo);

            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }
        return responseEntity;
    }
}
