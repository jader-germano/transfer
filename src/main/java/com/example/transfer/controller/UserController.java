package com.example.transfer.controller;

import com.example.transfer.model.dto.UserDTO;
import com.example.transfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user){
        return new ResponseEntity<UserDTO>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long UserId){
        return new ResponseEntity<UserDTO>(userService.getUserById(UserId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") long id, @RequestBody UserDTO User){
        return new ResponseEntity<UserDTO>(userService.updateUser(User, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){

        userService.deleteUser(id);

        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
    }
}
