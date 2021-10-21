package com.banco.svcslogin.controller;

import com.banco.svcslogin.request.UserRequest;
import com.banco.svcslogin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> creatUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @PostMapping("/logar")
    public ResponseEntity<String> validationUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.validateUser(userRequest));
    }

    @GetMapping("/verify-existence/")
    public String doesThisUserExist(@RequestParam String cpf){
        return userService.doesThisUserExist(cpf);
    }


}
