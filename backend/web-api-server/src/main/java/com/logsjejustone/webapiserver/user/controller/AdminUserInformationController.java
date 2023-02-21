package com.logsjejustone.webapiserver.user.controller;

import com.logsjejustone.webapiserver.user.domain.AdminUserInformation;
import com.logsjejustone.webapiserver.user.domain.AdminUserPassword;
import com.logsjejustone.webapiserver.user.service.AdminUserInformationService;
import com.logsjejustone.webapiserver.user.service.AdminUserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin-web/user")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminUserInformationController {

    @Autowired
    private AdminUserInformationService adminUserInformationService;

    @Autowired
    private AdminUserPasswordService adminUserPasswordService;


    // CREATE
    @PostMapping("")
    public ResponseEntity<AdminUserInformation> AddAdminUser(@RequestBody Map<String, String> employee) {
        System.out.println("[AdminUserInformation:AddAdminUser]" + employee);

        ResponseEntity<AdminUserInformation> responseEntity;
        HttpStatusCode statusCode = this.adminUserPasswordService.InsertAdminUserPw(employee).getStatusCode();
        if(statusCode.is2xxSuccessful()) {
            responseEntity = this.adminUserInformationService.AddAdminUser(employee);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }


    // READ
    @GetMapping("/all")
    public List<AdminUserInformation> GetAllAdminUsers() {
        System.out.println("[AdminUserInformation:GetAllAdminUsers]");

        return this.adminUserInformationService.GetAllAdminUsers();
    }

    @GetMapping("/available")
    public List<AdminUserInformation> GetAvailableAdminUsers() {
        System.out.println("[AdminUserInformation:GetAvailableAdminUsers]");

        return this.adminUserInformationService.GetAvailableAdminUsers();
    }

    @GetMapping("/password")
    public AdminUserPassword GetLatestPwHistory(@RequestBody Map<String, String> employee) {
        System.out.println("[AdminUserPassword:GetLatestPwHistory] employee:" + employee);

        return this.adminUserPasswordService.GetLatestPwHistory(employee.get("employeeNo"));
    }

    // UPDATE
    @PutMapping("/update/{targetEmpNo}")
    public ResponseEntity<AdminUserInformation> UpdateAdminUserInformation(@RequestBody AdminUserInformation adminUserInformation,
                                                                           @PathVariable String targetEmpNo) {
        System.out.println("[AdminUserInformation:UpdateAdminUserInformation]" + adminUserInformation + "\n Target:" + targetEmpNo);

        return this.adminUserInformationService.UpdateAdminUserInformation(adminUserInformation, targetEmpNo);
    }

    @PutMapping("/update")
    public ResponseEntity<AdminUserInformation> UpdateAdminUserPw(@RequestBody Map<String, String> employee) {
        System.out.println("[AdminUserInformation:UpdateAdminUserPw]" + employee);

        ResponseEntity<AdminUserInformation> responseEntity;
        HttpStatusCode statusCode = this.adminUserPasswordService.InsertAdminUserPw(employee).getStatusCode();
        if(statusCode.is2xxSuccessful()) {
            responseEntity = this.adminUserInformationService.UpdateAdminUserPw(employee);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

    // DELETE
    @PutMapping("/delete/{targetEmpNo}")
    public ResponseEntity<AdminUserInformation> DeleteAdminUser(@RequestBody AdminUserInformation adminUserInformation,
                                                                @PathVariable String targetEmpNo) {
        System.out.println("[AdminUserInformation:DeleteAdminUser]" + adminUserInformation + "\n Target:" + targetEmpNo);

        return this.adminUserInformationService.DeleteAdminUser(adminUserInformation, targetEmpNo);
    }

}
