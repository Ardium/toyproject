package com.logsjejustone.webapiserver.notice.service;

import com.logsjejustone.webapiserver.notice.domain.NoticeUser;
import com.logsjejustone.webapiserver.notice.repository.NoticeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeUserService {

    @Autowired
    private NoticeUserRepository noticeUserRepository;
    private LocalDateTime localDateTime = LocalDateTime.now();

    // CREATE
    public ResponseEntity<NoticeUser> PostNotice(NoticeUser noticeUser) {
        noticeUser.setUpdateDatetime(localDateTime);
        noticeUser.setRegisterDatetime(localDateTime);

        this.noticeUserRepository.save(noticeUser);

        return ResponseEntity.ok().build();
    }

    // READ
    public List<NoticeUser> GetNotices() {
        return this.noticeUserRepository.findAll();
    }

    // UPDATE
    public ResponseEntity<NoticeUser> PutNoticeById(NoticeUser noticeUser, Integer id) {
        Optional<NoticeUser> optNoticeUser = this.noticeUserRepository.findById(id);

        if(optNoticeUser.isEmpty()) {
            System.out.println("[ERROR:PutNoticeById] There is no data of id(" + id + ")");
            // TODO: 'throw Exception' 구문으로 변경해야 함
            return ResponseEntity.notFound().build();
        }
        else {
            NoticeUser afterNoticeUser = optNoticeUser.get();
            System.out.println("afterNoticeUser: " + afterNoticeUser.toString());

            afterNoticeUser.setUpdateDatetime(localDateTime);
            afterNoticeUser.setUpdateEmployeeNo(noticeUser.getUpdateEmployeeNo());

            // 사용자가 변경 가능한 데이터
            afterNoticeUser.setCategory(noticeUser.getCategory());
            afterNoticeUser.setTitle(noticeUser.getTitle());
            afterNoticeUser.setContent(noticeUser.getContent());

            this.noticeUserRepository.save(afterNoticeUser);
        }

        return ResponseEntity.ok().build();
    }

    // DELETE
    public ResponseEntity<NoticeUser> DeleteNoticeById(Integer id) {
        Optional<NoticeUser> optNoticeUser = this.noticeUserRepository.findById(id);

        if(optNoticeUser.isEmpty()) {
            System.out.println("[ERROR:DeleteNoticeById] There is no data of id(" + id + ")");
            // TODO: 'throw Exception' 구문으로 변경해야 함
            return ResponseEntity.notFound().build();
        }
        else {
            this.noticeUserRepository.delete(optNoticeUser.get());
        }
        return ResponseEntity.ok().build();
    }

}
