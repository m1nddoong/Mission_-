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

    // 댓글 생성
    @PostMapping("/article/{articleId}/comment")
    public String createComment(
            @PathVariable("articleId")
            Long id,
            @RequestParam("message")
            String message,
            @RequestParam("password")
            Long password
    ) {
        commentService.createComment(id, message, password);
        return "redirect:/article/{articleId}";
    }



}
