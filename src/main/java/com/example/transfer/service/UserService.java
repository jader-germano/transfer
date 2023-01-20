package com.example.transfer.service;

import com.example.transfer.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    UserDTO updateUser(UserDTO user, long id);
    void deleteUser(long id);


}
