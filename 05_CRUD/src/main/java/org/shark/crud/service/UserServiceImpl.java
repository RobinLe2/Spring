package org.shark.crud.service;

import org.shark.crud.model.dto.UserDTO;
import org.shark.crud.repository.UserDAO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  //--- Spring Container에 있는 BoardDAO 타입의 빈을 private final BoardDAO boardDAO 에 자동 주입하기 위한 생성자
public class UserServiceImpl implements UserService{
  
  private final UserDAO userDAO;
  
  @Override
  public UserDTO findUserByEmailAndPassword(UserDTO user) {

    return userDAO.getUser(user);
  }

}
