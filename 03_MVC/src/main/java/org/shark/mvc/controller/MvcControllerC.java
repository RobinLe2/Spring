package org.shark.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.shark.mvc.model.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/c")
@Controller
public class MvcControllerC {

  //----- List<BoardDTO> boards 반환
  private List<BoardDTO> getBoards() {
    List<BoardDTO> boards = new ArrayList<BoardDTO>();
    boards.add(new BoardDTO("제목1", 20));
    boards.add(new BoardDTO("제목2", 10));
    boards.add(new BoardDTO("제목3", 30));
    return boards;
  }
  
  //----- ModelAndView를 이용한 forward (데이터 전달)
  @RequestMapping("/list")
  public ModelAndView methodA() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("c/list");
    mv.addObject("boards", getBoards());  // JSP로 전달되는 데이터 boards
    return mv;
  }
  
  //----- Model을 이용한 forward (데이터 전달)
  @RequestMapping("/detail")  //----------- 요청 주소 : /c/detail?bid=1
  public String methodB(Model model  //---- JSP로 전달할 데이터를 저장할 Model
                      , int bid) {  //----- @RequestParam 생략
    model.addAttribute("board", getBoards().get(bid));  //-- JSP로 전달되는 데이터 board
    return "c/detail";
  }

}
