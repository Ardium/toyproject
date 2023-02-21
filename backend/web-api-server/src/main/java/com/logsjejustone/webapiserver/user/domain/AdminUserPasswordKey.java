package com.logsjejustone.webapiserver.user.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class AdminUserPasswordKey implements Serializable {
    private String employeeNo;
    private String no;
}
