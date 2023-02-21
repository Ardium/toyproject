package com.logsjejustone.webapiserver.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="AdminUserPasswordHistory")
@IdClass(AdminUserPasswordKey.class)
public class AdminUserPassword {
    @Id
    @NotNull
    @Column(length = 6)
    private String employeeNo;

    @Id
    @NotNull
    private String no;

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
    @Column(columnDefinition = "TEXT")
    private String currentPw;

    @Column(columnDefinition = "TEXT")
    private String previousPw1;

    @Column(columnDefinition = "TEXT")
    private String previousPw2;

    @NotNull
    @Column(length = 1)
    @ColumnDefault("0")
    private String pwTrialState;

    @NotNull
    @Column(length = 1)
    @ColumnDefault("'N'")
    private String temporaryPwState;
}
