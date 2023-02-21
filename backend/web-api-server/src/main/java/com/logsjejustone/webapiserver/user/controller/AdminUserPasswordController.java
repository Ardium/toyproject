package com.logsjejustone.webapiserver.user.controller;

import com.logsjejustone.webapiserver.user.domain.AdminUserPassword;
import com.logsjejustone.webapiserver.user.service.AdminUserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin-web/user/password")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminUserPasswordController {

    @Autowired
    private AdminUserPasswordService adminUserPasswordService;

    // READ
    @GetMapping("")
    public AdminUserPassword GetLatestPwHistory(@RequestBody Map<String, String> employee) {
        System.out.println("[AdminUserPassword:GetLatestPwHistory] employee:" + employee);

        return this.adminUserPasswordService.GetLatestPwHistory(employee.get("employeeNo"));
    }
}
