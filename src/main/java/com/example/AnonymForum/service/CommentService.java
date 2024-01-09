package com.example.AnonymForum.service;


import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.CommentEntity;
import com.example.AnonymForum.repository.ArticleRepository;
import com.example.AnonymForum.repository.CommentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // comment 생성
    public void createComment(
            Long id,
            String message,
            Long password
    ) {
        CommentEntity comment = new CommentEntity();
        comment.setMessage(message);
        comment.setPassword(password);
        commentRepository.save(comment);
    }
}
