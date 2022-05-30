package com.oanapintea.lotusvita.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class Credentials {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    private String email;
    private String password;


}
