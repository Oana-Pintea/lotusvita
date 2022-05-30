package com.oanapintea.lotusvita.service;

import com.oanapintea.lotusvita.entities.FilterLotus;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


// interfata utila pentru startegy design pattern si citibilitate
public interface FilterService {

    void save(FilterLotus filter, String email);

    List<FilterLotus> getAllFilters(String email);

    void update(Long id);

    void delete(Long id);
}
