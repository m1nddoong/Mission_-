package com.example.AnonymForum.service;

import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.BoardEntitiy;
import com.example.AnonymForum.repository.ArticleRepository;
import com.example.AnonymForum.repository.BoardRepository;
import java.util.Collections;
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
        Optional<BoardEntitiy> optionalBoardEntity = boardRepository.findById(boardId);
        optionalBoardEntity.ifPresent(article::setBoard);


        articleRepository.save(article);
    }

    // 어떤 특정 boardId 에 해당하는 게시판의 게시물들을 얻어오기
    public List<ArticleEntity> readArticles(Long boardId) {
        return articleRepository.findAllByBoardId(boardId);
    }

    // 모든 article 을 얻어오기
    public List<ArticleEntity> readAllArticles() {
        return articleRepository.findAll();
    }

    // articleId 로 article
    public ArticleEntity readOneArticle(Long id) {
        // Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(id);
        // return optionalArticleEntity.orElse(null);
        return articleRepository.findById(id).orElse(null);
    }


    // article 의 id 를 기준으로 내림차순 정렬하기
    public List<ArticleEntity> ArticleSortById(List<ArticleEntity> articles) {
        Collections.sort(articles);
        return articles;
    }


    public void updateArticle(
            Long id,
            String title,
            String content
    ) {
        ArticleEntity article = readOneArticle(id);
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
    }

}

