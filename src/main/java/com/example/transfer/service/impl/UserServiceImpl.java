package com.example.transfer.service.impl;

import com.example.transfer.TransferApplication;
import com.example.transfer.exception.ResourceNotFoundException;
import com.example.transfer.model.Account;
import com.example.transfer.model.User;
import com.example.transfer.model.dto.UserDTO;
import com.example.transfer.repository.AccountRepository;
import com.example.transfer.repository.UserRepository;
import com.example.transfer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(TransferApplication.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setCpf(userDTO.getCpf());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAccount(userDTO.getAccount());

        Account account = new Account();

        Random rnd = new Random();
        Integer accountNumber = 100000 + rnd.nextInt(900000);
        Integer digit = rnd.nextInt(2);
        user.setAccount(account);
        user.getAccount().setAccountNumber(accountNumber);
        user.getAccount().setDigit(digit);
        user.getAccount().setBalance(new BigDecimal("0"));

        userDTO = new UserDTO(userRepository.save(user));
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        return new UserDTO(user);

    }

    @Override
    public UserDTO updateUser(UserDTO user, long id) {

        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        existingUser.getAccount().setBalance(user.getAccount().getBalance());

        User userReturn = userRepository.save(existingUser);
        return new UserDTO(userReturn);
    }

    @Override
    public void deleteUser(long id) {

        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        userRepository.deleteById(id);
    }

    private UserDTO converter (User User) {
        UserDTO result = new UserDTO();
        User userDatabase = userRepository.getReferenceById(User.getId());
        result.setId(userDatabase.getId());
        result.setCpf(userDatabase.getCpf());
        result.setUsername(userDatabase.getUsername());
        result.setPassword(userDatabase.getPassword());
        result.setEmail(userDatabase.getEmail());
        result.setAccount(userDatabase.getAccount());
        return result;
    }

}
