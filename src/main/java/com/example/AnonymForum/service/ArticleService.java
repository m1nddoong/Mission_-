package com.example.AnonymForum.service;

import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.BoardEntitiy;
import com.example.AnonymForum.repository.ArticleRepository;
import com.example.AnonymForum.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public void createArticle(
            String title,
            String content,
            Long password,
            Long boardId
    ) {
        // 새로운 article 객체륾 만든다.
        ArticleEntity article = new ArticleEntity();
        article.setTitle(title);
        article.setContent(content);
        article.setPassword(password);

        // 게시판을 찾는다.
        Optional<BoardEntitiy> optionalBoardEntitiy
                = boardRepository.findById(boardId);

        article.setBoard(optionalBoardEntitiy.orElse(null));

        articleRepository.save(article);
    }

    public List<ArticleEntity> readArticles(Long boardId) {
        return articleRepository.findAllByBoardId(boardId);
        // return articleRepository.findAllByBoardId(boardId);
    }

    // articleId 로 article
    public Optional<ArticleEntity> readOneArticle(Long id) {
        return articleRepository.findById(id);
    }

}
