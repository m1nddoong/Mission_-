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
import org.springframework.web.bind.annotation.PostMapping;
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
        ArticleEntity articleEntity = articleService.readOneArticle(id);
        model.addAttribute("article", articleEntity);
        return "article/read";
    }

    @GetMapping("/{articleId}/update-view")
    public String updateArticleView(@PathVariable("articleId") Long id, Model model) {
        // 아이디를 가지고 해당 article 의 정보를 얻어오도록 service 에게 요청
        ArticleEntity article = articleService.readOneArticle(id);
        model.addAttribute("article", article);
        return "article/update";
    }

    // 게시글 수정 페이지에서 저장 버튼을 누르면 이 url 로 post 요청 온다 -> 처리
    @PostMapping("/{articleId}/update-view")
    public String updateArticle(
            @PathVariable("articleId")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content
    ) {
        articleService.updateArticle(id, title, content);
        return "redirect:/article/{articleId}";
    }
}
