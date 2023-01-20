package com.logsjejustone.webapiserver.user.repository;

import com.logsjejustone.webapiserver.user.domain.AdminUserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserInformationRepository extends JpaRepository<AdminUserInformation, String> {
    List<AdminUserInformation> findByUsageExpDateGreaterThanEqual(String usageExpDate);
}
