package com.example.AnonymForum.repository;

import com.example.AnonymForum.entity.ArticleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findAllByBoardId(Long boardId);
}