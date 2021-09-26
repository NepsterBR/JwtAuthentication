package com.banco.svcslogin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "clients")
@NoArgsConstructor
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;

    private String name;
    private String cpf;
    private String password;

    public String toString() {
        return "Client(idUser = " + this.getIdUser() +
                ", name = " + this.getName() +
                ", cpf = " + this.getCpf() + ")";
    }
}
