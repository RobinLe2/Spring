package org.shark.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.shark.crud.model.dto.UserDTO;
import org.shark.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

  private final UserService userService;

  @GetMapping("/login")
  public String loginForm(String currentURL
                        , HttpServletRequest request
                        , Model model) {
    
    //----- 로그인 페이지로 이동하기 전에 전달된 currentURL이 있으면 이를 redirectURL로 세팅합니다.
    
    //----- 로그인 페이지로 이동하기 전에 전달된 currentURL이 없으면 이전 주소를 기억하고 있는 요청 헤더 Referer를 참조합니다.
    if (currentURL == null || currentURL.isEmpty()) {
      //----- 이전 페이지를 기억하고 있는 요청 헤더 Referer (없을 수도 있음)
      String referer = request.getHeader("Referer");
      if (referer == null || referer.isEmpty() || referer.contains("/login")) {  //----- 로그인 성공 후 로그인 페이지로 이동하는 걸 막기 위해 /login 요청은 제외합니다.
        currentURL = "/";
      } else {
        currentURL = referer;
      }
    }
 
    //----- 로그인 페이지로 redirectURL 전달
    model.addAttribute("redirectURL", currentURL);
    
    //----- 로그인 페이지로 forward
    return "user/login";  //----- JSP 이름
    
  }
  
  @PostMapping("/login")
  public String loginProcess(UserDTO user
                           , String redirectURL
                           , HttpSession session
                           , RedirectAttributes redirectAttrs) {

    //----- 사용자 조회
    UserDTO loginUser = userService.findUserByEmailAndPassword(user);
    
    if (loginUser != null) {
      //----- 로그인 성공 시 사용자 nickname을 세션에 저장
      session.setAttribute("nickname", loginUser.getNickname());
      //----- redirectURL로 리다이렉트
      return "redirect:" + redirectURL;
    } else {
      //----- 로그인 실패 시 error 메시지 전달
      redirectAttrs.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
      //----- 로그인 실패 시 로그인 페이지로 redirectURL 요청 파라미터 전달
      redirectAttrs.addAttribute("redirectURL", redirectURL);
      //----- 로그인 페이지로 리다이렉트
      return "redirect:/user/login";
    }
    
  }
  
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    //----- 세션 초기화
    session.invalidate();
    //----- 메인 페이지로 리다이렉트
    return "redirect:/";
  }
  
}
