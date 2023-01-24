package com.example.transfer.service.impl;

import com.example.transfer.exception.ResourceNotFoundException;
import com.example.transfer.model.User;
import com.example.transfer.model.dto.AccountDTO;
import com.example.transfer.model.dto.UserDTO;
import com.example.transfer.repository.UserRepository;
import com.example.transfer.service.AccountService;
import com.example.transfer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setCpf(userDTO.getCpf());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        user = userRepository.save(user);
        accountService.saveAccount(new AccountDTO(user));
        UserDTO saveUserDto = new UserDTO(user);
        logger.info("User saved!");
        return saveUserDto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        logger.info("Users!");
        return userRepository
                .findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        return new UserDTO(user);

    }

    @Override
    public UserDTO updateUser(UserDTO user, Long id) {

        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        User userReturn = userRepository.save(existingUser);
        return new UserDTO(userReturn);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        userRepository.deleteById(id);
    }

    private UserDTO converter (User user) {
        UserDTO result = new UserDTO();
        User userDatabase = userRepository.getReferenceById(user.getId());
        result.setId(userDatabase.getId());
        result.setCpf(userDatabase.getCpf());
        result.setUsername(userDatabase.getUsername());
        result.setPassword(userDatabase.getPassword());
        result.setEmail(userDatabase.getEmail());
        return result;
    }

}
