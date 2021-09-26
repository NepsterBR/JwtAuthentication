package com.banco.svcslogin.request;

import lombok.Data;

@Data
public class UserRequest {

    private String cpf;
    private String name;
    private String password;
}
