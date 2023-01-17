package com.logsjejustone.webapiserver.notice.controller;

import com.logsjejustone.webapiserver.notice.domain.NoticeUser;
import com.logsjejustone.webapiserver.notice.service.NoticeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@CrossOrigin(origins = "http://localhost:8081")
public class NoticeUserController {
    @Autowired
    private NoticeUserService noticeUserService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<NoticeUser> PostNotice(@RequestBody NoticeUser noticeUser) {
        System.out.println("[NoticeUser:PostNotice] " + noticeUser);

        return this.noticeUserService.PostNotice(noticeUser);
    }

    // READ
    @GetMapping("")
    public List<NoticeUser> GetNotices() {
        System.out.println("[NoticeUser:GetNotices] ");
        return this.noticeUserService.GetNotices();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NoticeUser> PutNoticeById(@RequestBody NoticeUser noticeUser, @PathVariable Integer id) {
        System.out.println("[NoticeUser:PutNoticeById] " + noticeUser);

        return this.noticeUserService.PutNoticeById(noticeUser, id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<NoticeUser> DeleteNoticeById(@PathVariable Integer id) {
        System.out.println("[NoticeUser:DeleteNoticeById] " + id);

        return this.noticeUserService.DeleteNoticeById(id);
    }
}
