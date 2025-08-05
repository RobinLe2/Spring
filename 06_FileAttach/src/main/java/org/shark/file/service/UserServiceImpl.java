package org.shark.file.service;

import java.util.List;

import org.shark.file.model.dto.UserDTO;
import org.shark.file.repository.UserDAO;
import org.shark.file.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;
  private final FileUtil fileUtil;
  
  @Override
  public List<UserDTO> findAllUsers() {
    return userDAO.getAllUsers();
  }

  @Override
  public UserDTO findUserById(Integer uid) {
    return userDAO.getUserById(uid);
  }

  @Override
  public boolean signUp(UserDTO user, MultipartFile profile) {
    // TODO Auto-generated method stub
    return false;
  }

}
