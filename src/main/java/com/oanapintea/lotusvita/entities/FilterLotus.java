package com.oanapintea.lotusvita.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@Table(name = "filter_lotus")
@Entity
@NoArgsConstructor
public class FilterLotus {
    @Id // are nevoie de primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastChange;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextChange;
    private String email;

    public FilterLotus(String name, int type, LocalDate lastChange){
        this.name = name;
        this.type = type;
        this.lastChange = lastChange;
    }
}
