package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.Post;
import com.ujazdowski.SocialPortal.model.tables.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostsRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByUserOrderByDateDesc(User users, Pageable pageable);
}