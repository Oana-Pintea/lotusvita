package com.oanapintea.lotusvita.service;

import com.oanapintea.lotusvita.entities.Credentials;

public interface LoginService {
    boolean check(Credentials credentials);

    boolean save(Credentials credentials);
}
