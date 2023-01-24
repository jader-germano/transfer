package com.example.transfer.service;

import com.example.transfer.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO user, Long id);
    void deleteUser(Long id);


}
