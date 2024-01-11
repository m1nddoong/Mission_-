package com.example.AnonymForum.service;


import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.CommentEntity;
import com.example.AnonymForum.repository.ArticleRepository;
import com.example.AnonymForum.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    // comment 생성
    public void createComment(
            String message,
            Long password,
            Long articleId
    ) {
        CommentEntity comment = new CommentEntity();
        comment.setMessage(message);
        comment.setPassword(password);

        // 게시물을 찾는다.
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        optionalArticleEntity.ifPresent(comment::setArticle);

        commentRepository.save(comment);
    }

    // 특정 aritcleId 에 해당하는 comment 만 찾기
    public List<CommentEntity> readAllCommentsById(Long articleId) {
        List<CommentEntity> comments = commentRepository.findAllByArticleId(articleId);
        return comments;
        //return commentRepository.findAllByArticleId(articleId);
    }

    // 특정 commentId 에 해당하는 댓글 하나 찾기
    public CommentEntity readOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }



    public void deleteComment(
            Long articleId,
            Long commentId,
            Long password
    ) {
        CommentEntity comment = readOneCommentById(commentId);
        // 비밀번호 일치 조건
        if (comment.getPassword().equals(password)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
