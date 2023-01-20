package com.example.transfer.model.dto;

import com.example.transfer.model.Account;
import com.example.transfer.model.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {
    private long id;

    private String username;

    private String cpf;

    private String password;

    private String email;

    private Account account;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.cpf = user.getCpf();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.account = user.getAccount();
    }

    public UserDTO() {
    }
}
