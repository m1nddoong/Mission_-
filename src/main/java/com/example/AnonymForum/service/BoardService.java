package com.example.AnonymForum.service;

import com.example.AnonymForum.entity.BoardEntitiy;
import com.example.AnonymForum.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardEntitiy readOneBoard(Long id) {
        Optional<BoardEntitiy> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

    public List<BoardEntitiy> readAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<BoardEntitiy> readBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
