package org.shark.file.controller;

import java.util.List;
import java.util.Map;

import org.shark.file.model.dto.NoticeDTO;
import org.shark.file.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

  private final NoticeService noticeService;
  
  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("notices", noticeService.findNotices());
    return "notice/list";
  }
  
  @GetMapping("/write")
  public String writeForm() {
    return "notice/write";
  }
  
  @PostMapping("/write")
  public String write(NoticeDTO notice  //------------------------------------ title, content 정보를 받습니다.
                    , @RequestParam("files") List<MultipartFile> files  //---- <input type="file" multiple> 양식을 받을 때는 List나 배열을 사용합니다.
                    , RedirectAttributes redirectAttr) {
    boolean result = noticeService.addNotice(notice, files);
    redirectAttr.addFlashAttribute("msg", result ? "공지사항 등록 성공" : "공지사항 등록 실패");
    return "redirect:/notice/list";
  }
  
  @GetMapping("/detail")
  public String detail(Integer nid
                     , Model model) {
    Map<String, Object> map = noticeService.findNoticeById(nid);
    model.addAttribute("notice", map.get("notice"));
    model.addAttribute("attaches", map.get("attaches"));
    return "notice/detail";
  }
  
  @GetMapping("/remove")
  public String remove(Integer nid
                     , RedirectAttributes redirectAttr) {
    boolean result = noticeService.deleteNotice(nid);
    redirectAttr.addFlashAttribute("msg", result ? "공지사항 삭제 성공" : "공시사항 삭제 실패");
    return "redirect:/notice/list";
  }
  
}
