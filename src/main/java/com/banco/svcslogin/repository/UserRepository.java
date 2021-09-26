package com.banco.svcslogin.repository;

import com.banco.svcslogin.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCpf(String name);
}
