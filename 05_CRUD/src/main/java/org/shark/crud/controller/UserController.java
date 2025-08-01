package org.shark.crud.controller;

import javax.servlet.http.HttpServletRequest;

import org.shark.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
  private final UserService userService;
  
  @GetMapping("/login")
  public String loginForm(String redirectURL
                          , HttpServletRequest request
                          , Model model) {
    
    //----- 로그인 페이지로 이동하기 전에 전달된 redirectURL이 있으면 전달
    if (redirectURL == null || redirectURL.isEmpty()) {
      //----- 이전 페이지를 기억하고 있는 요청 헤더 Referer 참고 (없을 수도 있음)
      String referer = request.getHeader("Referer");
      if (referer == null || referer.isEmpty()) {
        redirectURL = "/";
      }else {
        redirectURL = referer;
      }
    }
    //--- 로그인 페이지로 redirectURL 전달
    model.addAttribute("redirectURL", redirectURL);
    
    //--- 로그인 페이지로 forward
    return "user/login";  //-------  JSP 이름
   
    
    
  }
}
