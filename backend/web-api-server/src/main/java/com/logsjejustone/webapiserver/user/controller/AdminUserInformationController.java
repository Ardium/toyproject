package com.logsjejustone.webapiserver.user.controller;

import com.logsjejustone.webapiserver.user.domain.AdminUserInformation;
import com.logsjejustone.webapiserver.user.service.AdminUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-web/users")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminUserInformationController {

    @Autowired
    private AdminUserInformationService adminUserInformationService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<AdminUserInformation> AddAdminUser(@RequestBody AdminUserInformation adminUserInformation) {
        System.out.println("[AdminUserInformation:AddAdminUser]" + adminUserInformation);

        return this.adminUserInformationService.AddAdminUser(adminUserInformation);
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

    // UPDATE
    @PutMapping("/{targetEmpNo}")
    public ResponseEntity<AdminUserInformation> UpdateAdminUserInformation(@RequestBody AdminUserInformation adminUserInformation,
                                                                           @PathVariable String targetEmpNo) {
        System.out.println("[AdminUserInformation:UpdateAdminUserInformation]" + adminUserInformation + "\n Target:" + targetEmpNo);

        return this.adminUserInformationService.UpdateAdminUserInformation(adminUserInformation, targetEmpNo);
    }

    // DELETE
    @DeleteMapping("/{targetEmpNo}")
    public ResponseEntity<AdminUserInformation> DeleteAdminUser(@RequestBody AdminUserInformation adminUserInformation,
                                                                @PathVariable String targetEmpNo) {
        System.out.println("[AdminUserInformation:DeleteAdminUser]" + adminUserInformation + "\n Target:" + targetEmpNo);

        return this.adminUserInformationService.DeleteAdminUser(adminUserInformation, targetEmpNo);
    }

}
