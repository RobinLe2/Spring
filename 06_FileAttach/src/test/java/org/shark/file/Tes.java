package org.shark.file;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.file.repository.NoticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //----- JUnit4를 이용한 테스트

@ContextConfiguration(locations = {  //------------------------------------------ Test 시 필요한 빈(Bean)을 찾을 장소를 작성합니다. (root-context.xml의 4개 빈과 BoardDAO 빈)
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"  //------------------- root-context.xml
  , "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"  //----- @Repository는 component-scan이 있어야 동작하므로 추가
})

public class Tes{

  @Autowired
  private NoticeDAO noticeDAO;
  
  @Test
  void ddtest() {
    assertNotNull(noticeDAO.getNotices());
    
  }
  
}
