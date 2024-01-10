package com.example.AnonymForum.controller;

import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.BoardEntitiy;
import com.example.AnonymForum.entity.CommentEntity;
import com.example.AnonymForum.repository.CommentRepository;
import com.example.AnonymForum.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/article/{articleId}/comment")
    public String readAllComments(
            @PathVariable("articleId")
            Long id,
            Model model
    ) {
        model.addAttribute("comments", commentService.readAllCommentsById(id));
        return "article/read";
    }

    // 댓글 생성
    @PostMapping("/article/{articleId}/comment")
    public String createComment(
            @RequestParam("message")
            String message,
            @RequestParam("password")
            Long password,
            @PathVariable("articleId")
            Long articleId
    ) {
        commentService.createComment(message, password, articleId);
        return "redirect:/article/{articleId}";
    }
}
