package com.logsjejustone.webapiserver.user.controller;

import com.logsjejustone.webapiserver.user.domain.AdminUserPassword;
import com.logsjejustone.webapiserver.user.service.AdminUserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin-web/user/password")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminUserPasswordController {

    @Autowired
    private AdminUserPasswordService adminUserPasswordService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<AdminUserPassword> InsertAdminUserPw(@RequestBody Map<String, String> adminUserPw) {
        System.out.println("[AdminUserPassword:InsertAdminUserPw]" + adminUserPw);

        return this.adminUserPasswordService.InsertAdminUserPw(adminUserPw);
    }

    // READ
    @GetMapping("")
    public AdminUserPassword GetLatestPwHistory(@RequestBody Map<String, String> employee) {
        System.out.println("[AdminUserPassword:GetLatestPwHistory] employee:" + employee);

        return this.adminUserPasswordService.GetLatestPwHistory(employee.get("employeeNo"));
    }
}
