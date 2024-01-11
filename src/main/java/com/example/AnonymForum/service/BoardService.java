package com.example.AnonymForum.service;

import com.example.AnonymForum.entity.BoardEntity;
import com.example.AnonymForum.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardEntity readOneBoard(Long id) {
        Optional<BoardEntity> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

    public List<BoardEntity> readAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<BoardEntity> readBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
