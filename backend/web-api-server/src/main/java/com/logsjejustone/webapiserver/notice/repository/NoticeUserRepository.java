package com.logsjejustone.webapiserver.notice.repository;

import com.logsjejustone.webapiserver.notice.domain.NoticeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeUserRepository extends JpaRepository<NoticeUser, Integer> {

}
