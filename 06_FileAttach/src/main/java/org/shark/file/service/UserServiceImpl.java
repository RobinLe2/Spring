package org.shark.file.service;

import java.util.List;

import org.shark.file.model.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public class UserServiceImpl implements UserService {

  
  
  @Override
  public List<UserDTO> findAllUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserDTO findUserById(Integer uid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean signUp(UserDTO user, MultipartFile profile) {
    // TODO Auto-generated method stub
    return false;
  }

}
