package com.ujazdowski.SocialPortal.service;

import com.ujazdowski.SocialPortal.model.tables.Post;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.InvitationsRepository;
import com.ujazdowski.SocialPortal.repository.PostsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final InvitationsService invitationsService;
    private final PostsRepository postsRepository;

    public PostService(InvitationsRepository invitationsRepository, InvitationsService invitationsService, PostsRepository postsRepository) {
        this.invitationsService = invitationsService;
        this.postsRepository = postsRepository;
    }

    public Page<Post> getUserFriendsPosts(User user, Pageable pageable)
    {
        List<User> friends = this.invitationsService.getFriends(user.getUserId());
        friends.add(user);
        return postsRepository.findAllByUserInOrderByDateDesc(friends, pageable);
    }
}
