package com.logsjejustone.webapiserver.notice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NoticeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(length = 6)
    @NotNull
    private String registerEmployeeNo;
    @CreatedDate
    @NotNull
    private LocalDateTime registerDatetime;

    @Column(length = 6)
    @NotNull
    private String updateEmployeeNo;
    @CreatedDate
    @NotNull
    private LocalDateTime updateDatetime;

    @Column(length = 20)
    private String category;
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
}
