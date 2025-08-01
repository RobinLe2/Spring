package org.shark.crud.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/*
 * @ControllerAdvice
 * 
 * 1. 스프링에서 여러 컨트롤러에 공통적으로 적용할 수 있는 전역 컨트롤러 조인(Advice) 클래스에 사용하는 어노테이션입니다.
 * 2. 주로 예외 처리(@ExceptionHandler), 특정 모델 속성의 전역 바인딩(@ModelAttribute), 데이터 바인딩 초기화(@InitBinder) 등의 목적으로 사용합니다.
 * 3. 내부적으로 @Component를 포함하고 있어서 Spring Container에 빈(Bean)으로 등록됩니다.
 * 4. @RestControllerAdvice는 @ControllerAdvice + @ResponseBody를 합친 것으로, REST API 응답 바디 형태로 예외 메시지 등을 리턴할 때 사용합니다.
 */

@ControllerAdvice  //----- 모든 Controller가 동작할 때 함께 동작합니다.
public class GlobalControllerAdvice {

  //----- 모든 JSP에서 ${currentURL} 형식으로 현재 화면의 주소를 확인할 수 있습니다.
  
  @ModelAttribute("currentURL")
  public String currentURL(HttpServletRequest request) throws Exception {
    String requestURL = request.getRequestURL().toString();
    String queryString = request.getQueryString();
    return URLEncoder.encode(requestURL + (queryString != null ? "?" + queryString : ""), "UTF-8");
  }
  
}
