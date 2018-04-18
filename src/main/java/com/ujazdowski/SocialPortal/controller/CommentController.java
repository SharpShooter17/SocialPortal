package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.exceptions.UnauthorizedAcctionException;
import com.ujazdowski.SocialPortal.model.tables.Comment;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.CommentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.net.MalformedURLException;
import java.util.Date;

@Controller
@RequestMapping("/home/post/comment")
public class CommentController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentsRepository commentsRepository;

    public CommentController(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, params = {"postId", "comment"})
    public ModelAndView comment(@RequestParam("postId") Long postId,
                                @RequestParam("comment") String comment) throws UnauthorizedAcctionException, MalformedURLException {
        User logged = SocialPortalUtils.getLoggedUser();
        Comment c = new Comment();

        c.setComment(comment);
        c.setUser(logged);
        c.setDate(new Date());
        c.setPostId(postId);

        this.commentsRepository.save(c);

        return new ModelAndView(new RedirectView( "/home/post/" + postId ));
    }

}
