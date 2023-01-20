package com.logsjejustone.webapiserver.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AdminUserInformation {

    @Id
    @NotNull
    @Column(length = 6)
    private String employeeNo;

    @NotNull
    @Column(length = 6)
    private String registerEmployeeNo;

    @NotNull
    private LocalDateTime registerDatetime;

    @NotNull
    @Column(length = 6)
    private String updateEmployeeNo;

    @NotNull
    private LocalDateTime updateDatetime;

    @NotNull
    @Column(length = 20)
    private String employeeName;

    @Column(length = 50)
    private String employeeNameEng;

    @NotNull
    @Column(length = 20)
    private String employeeCompany;

    @NotNull
    @Column(length = 20)
    private String employeeDivision;

    @NotNull
    @Column(length = 20)
    private String employeeTeam;

    @Column(length = 10)
    private String employeePosition;

    @NotNull
    @Column(length = 50)
    private String employeeEmail;

    @NotNull
    @Column(length = 13)
    private String employeePhone;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String employeePw;

    @NotNull
    @Column(length = 8)
    private String employeePwExpDate;

    @NotNull
    @Column(length = 8)
    private String usageExpDate;
}
