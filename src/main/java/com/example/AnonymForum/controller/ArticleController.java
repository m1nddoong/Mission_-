package com.example.AnonymForum.controller;


import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.BoardEntitiy;
import com.example.AnonymForum.entity.CommentEntity;
import com.example.AnonymForum.service.ArticleService;
import com.example.AnonymForum.service.BoardService;
import com.example.AnonymForum.service.CommentService;
import java.util.List;
import java.util.Optional;
import javax.xml.stream.events.Comment;
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
    private final CommentService commentService;
    private final BoardService boardService;


    @GetMapping("{articleId}")
    public String readOneArticle(@PathVariable("articleId") Long id, Model model) {
        ArticleEntity articleEntity = articleService.readOneArticle(id);
        List<CommentEntity> comments = commentService.readAllCommentsById(id);
        model.addAttribute("article", articleEntity);
        model.addAttribute("comments", comments);

        // 뒤로 가기 버튼을 위한 board 객체 가져와 모델에 추가
        Long boardId = articleEntity.getBoard().getId();
        BoardEntitiy board = boardService.readOneBoard(boardId);
        model.addAttribute("board", board);

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
            String content,
            @RequestParam("password")
            Long password
    ) {
        try {
            articleService.updateArticle(id, title, content, password);
            return "redirect:/article/{articleId}";
        } catch (RuntimeException e) {
            // 비밀번호가 일치하지 않을 경우 에러 메세지와 함꼐 다시 수정 폼으로 이동
            return "redirect:/article/" + id + "/update-view?error=password";
        }
    }

    @PostMapping("{articleId}/delete")
    public String deleteArticle(
            @PathVariable("articleId")
            Long id,
            @RequestParam("password")
            Long password
    ) {
        try {
            articleService.deleteArticle(id, password);
            return "redirect:/boards";
        } catch (RuntimeException e) {
            return "redirect:/article/" + id + "?articleError=password";
        }
    }


}
