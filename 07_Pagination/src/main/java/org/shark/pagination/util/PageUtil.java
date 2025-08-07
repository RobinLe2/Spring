package org.shark.pagination.util;

import java.util.Map;

import org.shark.pagination.model.dto.PageDTO;
import org.springframework.stereotype.Component;

/**
 * 페이징 계산, 페이징 UI(HTML) 생성 유틸리티 클래스
 */

@Component  //----- Spring Container에 PageUtil 타입의 빈이 등록됩니다.
public class PageUtil {

  //----- 한 블록 당 표시할 페이지의 개수
  private static final int PAGE_PER_BLOCK = 10;
 
  /**
   * 페이징 정보를 계산해서 PageDTO에 저장하는 메소드
   */
  public void calculatePaging(PageDTO dto) {
    int page = dto.getPage();
    int size = dto.getSize();
    int itemCount = dto.getItemCount();
    
    int offset = (page - 1) * size;
    int pageCount = (int) Math.ceil((double) itemCount / size);
    int beginPage = ((page - 1) / PAGE_PER_BLOCK) * PAGE_PER_BLOCK + 1;
    int endPage = Math.min(beginPage + PAGE_PER_BLOCK - 1, pageCount);
    
    dto.setOffset(offset);
    dto.setPageCount(pageCount);
    dto.setBeginPage(beginPage);
    dto.setEndPage(endPage);
  }
  
  /**
   * 페이지 이동 링크(HTML) 생성
   */
  public String getPagingHtml(PageDTO dto, String requestURL, Map<String, Object> params) {
    
    //----- 페이지 이동 링크 생성에 필요한 변수
    int page = dto.getPage();
    int pageCount = dto.getPageCount();
    int beginPage = dto.getBeginPage();
    int endPage = dto.getEndPage();
    
    //----- 쿼리 스트링 만들기
    String queryString = "";
    if (params != null) {
      StringBuilder queryStringBuilder = new StringBuilder();
      for (Map.Entry<String, Object> entry : params.entrySet()) {
        queryStringBuilder.append("&");
        queryStringBuilder.append(entry.getKey());
        queryStringBuilder.append("=");      
        queryStringBuilder.append(entry.getValue());      
      }
      queryString = queryStringBuilder.toString();
    }
    
    StringBuilder builder = new StringBuilder();
    
    //----- 이전 블록 ( < )
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURL + "?page=" + (beginPage - 1) + queryString + "'\">&lt;</button>");
    
    //----- 페이지 ( 1 2 3 4 5 6 7 8 9 10 )
    for (int p = beginPage; p <= endPage; p++) {
      if (p == page)
        builder.append("<button type=\"button\" class=\"focus-page\" onclick=\"location.href='" + requestURL + "?page=" + (p) + queryString + "'\">" + p + "</button>");
      else
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURL + "?page=" + (p) + queryString + "'\">" + p + "</button>");        
    }
    
    //----- 다음 블록 ( > )
    if(endPage == pageCount)
      builder.append("<button type=\"button\" class=\"disabled-button\">&gt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURL + "?page=" + (endPage + 1) + queryString + "'\">&gt;</button>");
    
    return builder.toString();
    
  }
  
}
