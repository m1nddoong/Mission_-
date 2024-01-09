package com.example.AnonymForum.controller;

import com.example.AnonymForum.entity.ArticleEntity;
import com.example.AnonymForum.entity.BoardEntitiy;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("boards")
public class BoardController {

    private final BoardService boardService;
    private final ArticleService articleService;

    // 전체 게시판 목록 보가
    @GetMapping("")
    public String readAllBoards(Model model) {
        model.addAttribute("AllBoards", boardService.readAllBoards());
        return "board/home";
    }


    // 선택한 id의 게시판 보기
    // boardId = 2 에 해당하는 게시글을 보여주면 된다.
    @GetMapping("/{boardId}")
    public String readOneBoard(@PathVariable("boardId") Long boardId, Model model) {
        List<ArticleEntity> articles;

        if (boardId.equals(1L)) {
            // 전체 게시판인 경우 모든 게시물 가져오기
            articles = articleService.readAllArticles();
        } else {
            // 특정 게시판인 경우 해당 게시판의 게시물 가져오기
            articles = articleService.readArticles(boardId);
        }
        // id 를 기준으로 내림차순
        model.addAttribute("articles", articleService.ArticleSortById(articles));

        // 게시판 정보 가져오기
        Optional<BoardEntitiy> optionalBoardEntity = boardService.readBoardById(boardId);
        optionalBoardEntity.ifPresent(boardEntity -> model.addAttribute("board", boardEntity));

        return "board/read";
    }



    // 몇번(id) 게시판에, 게시글 작성화면 보기
    @GetMapping("/create-view")
    public String create(Model model) {
        List<BoardEntitiy> boardEntitiyList = boardService.readAllBoards();
        model.addAttribute("AllBoards", boardEntitiyList);
        // 게시글 작성 화면 보여주기 (Get)
        return "board/create";
    }

    // 몇번(id) 게시판에, 게시글 작성하고 전송
    @PostMapping("/create-view")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            Long password,
            @RequestParam("boardId")
            Long boardId
    ) {
        articleService.createArticle(title, content, password, boardId);
        return "redirect:/boards";
    }
}
