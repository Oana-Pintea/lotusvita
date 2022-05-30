package com.oanapintea.lotusvita.service;

import com.oanapintea.lotusvita.entities.Credentials;
import com.oanapintea.lotusvita.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public boolean check(Credentials credentials) {
        List<Optional<Credentials>> pass = loginRepository.findByPass(credentials.getPassword());
        Optional<Credentials> user = loginRepository.findByEmail(credentials.getEmail());
        if (user.isPresent()) {
            if (pass.stream().anyMatch(Optional::isPresent)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean save(Credentials credentials) {
        if (loginRepository.findByEmail(credentials.getEmail()).isPresent()) {
            return false;
        }
        loginRepository.save(credentials);
        return true;
    }
}
