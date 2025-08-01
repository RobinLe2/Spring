package org.shark.crud.service;

import java.util.List;

import org.shark.crud.model.dto.BoardDTO;
import org.shark.crud.repository.BoardDAO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
  
  private final BoardDAO boardDAO;
      
  @Override
  public List<BoardDTO> findAllBoards() {
    return boardDAO.getBoards();
  }

  @Override
  public BoardDTO findBoardById(int bid) {
    return boardDAO.getBoardById(bid);
  }

  @Override
  public boolean addBoard(BoardDTO board) {
    return boardDAO.insertBoard(board) == 1;
  }

  @Override
  public boolean modifyBoard(BoardDTO board) {
    // TODO Auto-generated method stub
    return boardDAO.updateBoard(board) == 1;
  }

  @Override
  public boolean removeBoard(int bid) {
    // TODO Auto-generated method stub
    return boardDAO.deleteBoardById(bid) == 1;
  }

}
