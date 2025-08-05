package org.shark.crud.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

  public XSSRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  //----- 요청 파라미터를 cleanXSS() 메소드 결과로 바꿔서 반환합니다.
  @Override
  public String getParameter(String name) {
    String param = super.getParameter(name);
    if (param == null) {
      return null;
    }
    return cleanXSS(param);
  }
  
  //----- 스크립트 코드가 입력되면 이를 엔티티 코드로 변환하여 스크립트 코드의 동작을 무력화 합니다.
  private String cleanXSS(String param) {
    param = param.replaceAll("<", "&lt;")
                 .replaceAll(">", "&gt;")
                 .replaceAll("'", "&#39;")
                 .replaceAll("\\(", "&#40;")
                 .replaceAll("\\)", "&#41;")
                 .replaceAll("eval\\((.*)\\)", "");
    return param;
  }
  
}
