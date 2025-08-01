package org.shark.crud.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.shark.crud.model.dto.UserDTO;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //----- Spring Container에 있는 SqlSessionTemplate 타입의 빈을 
                          //      private final SqlSessionTemplate template에 자동 주입(@Autowired)하기 위한 생성자
@Repository  //------------------ DAO 레벨에서 사용하는 @Component
public class UserDAO {

  private final SqlSessionTemplate template;
  
  //----- 조회 (회원 정보)
  public UserDTO getUser(UserDTO user) {
    return template.selectOne("mybatis.mapper.userMapper.getUser", user);
  }
  
}
