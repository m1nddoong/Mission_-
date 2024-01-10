package com.example.AnonymForum.repository;

import com.example.AnonymForum.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByArticleId(Long articleId);
}
