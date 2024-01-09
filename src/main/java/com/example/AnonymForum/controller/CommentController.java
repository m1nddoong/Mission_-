package com.example.AnonymForum.controller;

import com.example.AnonymForum.repository.CommentRepository;
import com.example.AnonymForum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/article/{articleId}/comment")
    public String createComment() {

        return "redirect:/article/read";
    }

}
