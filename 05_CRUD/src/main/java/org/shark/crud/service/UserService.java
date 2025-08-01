package org.shark.crud.service;

import org.shark.crud.model.dto.UserDTO;

// 주요 네이밍 : get, find, create, add, update, modify, delete, remove 등

public interface UserService {
  UserDTO findUserByEmailAndPassword(UserDTO user);
}
