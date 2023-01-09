package com.logsjejustone.webapiserver.notice.repository;

import com.logsjejustone.webapiserver.notice.domain.NoticeUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeUserRepositoryTest {
    @Autowired
    private NoticeUserRepository noticeUserRepository;

    @Test
    public void createNotice () {
        // Check 1
        LocalDateTime now = LocalDateTime.now();
        String employeeNo = "000000";

        NoticeUser noticeUser = new NoticeUser();
        noticeUser.setRegisterDatetime(now);
        noticeUser.setRegisterEmployeeNo(employeeNo);
        noticeUser.setUpdateDatetime(now);
        noticeUser.setUpdateEmployeeNo(employeeNo);

        noticeUser.setCategory("시스템 점검");
        noticeUser.setTitle("2022/11/30 시스템 점검 공지");
        noticeUser.setContent("아래와 같은 일정으로 시스템 점검 예정임.");

        this.noticeUserRepository.save(noticeUser);
        assertEquals(1, this.noticeUserRepository.count());
    }

    @Test
    public void readNotice () throws Exception {
        // Check 1
        List<NoticeUser> lstNoticeUser = this.noticeUserRepository.findAll();
        assertEquals(2, lstNoticeUser.size());
    }

    @Test
    public void updateNotice () throws Exception {
        // Check 1
        Optional<NoticeUser> optNotice = this.noticeUserRepository.findById(2);
        assertTrue(optNotice.isPresent());

        // Check 2
        NoticeUser noticeUser = optNotice.get();
        assertEquals("2022/11/30 시스템 점검 공지", noticeUser.getTitle());

        // Check 3
        String strAfter = "2022/12/30 시스템 점검 공지";
        noticeUser.setTitle(strAfter);
        this.noticeUserRepository.save(noticeUser);
        assertEquals(strAfter, optNotice.get().getTitle());
    }

    @Test
    public void deleteNotice () throws Exception {
        // Check 1
        Optional<NoticeUser> optNotice = this.noticeUserRepository.findById(2);
        assertTrue(optNotice.isPresent());

        // Check 2
        NoticeUser noticeUser = optNotice.get();
        assertEquals("2022/12/30 시스템 점검 공지", noticeUser.getTitle());

        // Check 3
        this.noticeUserRepository.delete(noticeUser);
        assertEquals(0, this.noticeUserRepository.count());
    }
}