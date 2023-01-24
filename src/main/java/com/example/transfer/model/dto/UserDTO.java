package com.example.transfer.model.dto;

import com.example.transfer.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private long id;

    private String username;

    private String cpf;

    private String password;

    private String email;


    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.cpf = user.getCpf();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

}
