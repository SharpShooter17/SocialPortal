package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
