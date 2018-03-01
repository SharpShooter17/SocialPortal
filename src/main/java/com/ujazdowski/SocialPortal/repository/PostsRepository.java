package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PostsRepository extends JpaRepository<Post, Long> {
}
