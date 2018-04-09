package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.Pictrue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface PictruesRepository extends JpaRepository<Pictrue, Long> {
    @Query(value = "SELECT p.pictrueId FROM Pictrue p WHERE p.user.userId = :userId")
    Set<Long> getPictrueIdsByUser_UserId(@Param("userId") Long userId);
}
