package com.example.AnonymForum.controller;


import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.service.ArticleService;
import com.example.AnonymForum.service.BoardService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {

    private final ArticleService articleService;
    private final BoardService boardServiceq;

    @GetMapping("{articleId}")
    public String readOneArticle(@PathVariable("articleId") Long id, Model model) {
        Optional<ArticleEntity> articleEntityList = articleService.readOneArticle(id);
        model.addAttribute("article", articleEntityList);
        return "article/read";
    }


}
