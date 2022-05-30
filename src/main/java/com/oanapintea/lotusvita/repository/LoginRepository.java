package com.oanapintea.lotusvita.repository;

import com.oanapintea.lotusvita.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository <Credentials, Long> {
    @Query("select c from Credentials c where upper(c.password) = upper(?1)")
    List<Optional<Credentials>> findByPass(String password);

    @Query("select c from Credentials c where upper(c.email) = upper(?1)")
    Optional<Credentials> findByEmail(String email);
}
