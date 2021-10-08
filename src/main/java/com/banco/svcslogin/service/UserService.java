package com.banco.svcslogin.service;

import com.banco.svcslogin.entity.Client;
import com.banco.svcslogin.repository.UserRepository;
import com.banco.svcslogin.request.UserRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //TODO colocar em variavel de ambiente
    private final SecretKey KEY = Keys.hmacShaKeyFor(
            "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));

    public String createUser(UserRequest userRequest) {
        if (userRepository.findByCpf(userRequest.getCpf()).isPresent()){
            return "cpf já cadastrado no sistema";
        }
        try {
            var newUser = new Client();
            newUser.setCpf(userRequest.getCpf());
            newUser.setPassword( passwordEncoder.encode(userRequest.getPassword()));
            newUser.setName(userRequest.getName());
            userRepository.save(newUser);
            return "Usuario criado com sucesso!";
        } catch (Exception e) {
            return "não foi possivel criar usuario";
        }
    }

    public String validateUser(UserRequest userRequest) {
        var name = userRepository.findByCpf(userRequest.getCpf());
        try {
            if (!passwordEncoder.matches(userRequest.getPassword(), name.get().getPassword())) {
                return "Usuário e/ou senha inválidos.";
            } else {
                return Jwts.builder()
                        .setSubject(name.get().toString())
                        .setIssuer("Let's Code Bank")
                        .setIssuedAt(new Date())
                        .setExpiration(
                                Date.from(
                                        LocalDateTime.now().plusMinutes(15L)
                                                .atZone(ZoneId.systemDefault())
                                                .toInstant()))
                        .signWith(SignatureAlgorithm.HS256, KEY)
                        .compact();
            }
        } catch (Exception ex) {
            return "Não foi possivel validar o usuário.";
        }
    }

    public String doesThisUserExist(String cpf){
        var client = userRepository.findByCpf(cpf);
        return client.map(value -> cpf + "," + value.getName()).orElse("UserNotFound");
    }



}
