package com.oanapintea.lotusvita.service;

import com.oanapintea.lotusvita.entities.FilterLotus;
import com.oanapintea.lotusvita.repository.FilterRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class FilterServiceImpl implements FilterService {
    @Autowired
    FilterRepository filterRepository;


    @Override
    public void save(FilterLotus filter, String email) {
        // in functie de ce tip am ales la filtru ii punem odata viitoare de schimb
        switch (filter.getType()) {
            // lastchangedate e data curenta ca abia ce adaugam filtrul
            case 1 -> filter.setNextChange(filter.getLastChange().plusWeeks(1));
            case 2 -> filter.setNextChange(filter.getLastChange().plusWeeks(2));
            case 3 -> filter.setNextChange(filter.getLastChange().plusWeeks(3));
        }
        filter.setEmail(email);
        // asta stie repository sa faca (sa salveze in prostgres)
        FilterLotus filterLotusSaved = filterRepository.save(filter);
        // just simple loggs
        log.info("Adding filter " + filterLotusSaved);
    }

    @Override
    public List<FilterLotus> getAllFilters(String email) {
        // stie repo
        return filterRepository.findAllByEmail(email);
    }

    @Override
    public void update(Long id) {
        // cautam cu ajutorul lui repo filtrul primit ca id
        FilterLotus filter = filterRepository.findById(id).get();
        // ii spunem ca l-am schimbat deci last change e data de azi
        filter.setLastChange(LocalDate.now());
        // ii setam data viitoare de schimbare
        switch (filter.getType()) {
            case 1 -> filter.setNextChange(filter.getLastChange().plusWeeks(1));
            case 2 -> filter.setNextChange(filter.getLastChange().plusWeeks(2));
            case 3 -> filter.setNextChange(filter.getLastChange().plusWeeks(3));
        }
        // si il punem la loc cu ajutorul repo
        filterRepository.save(filter);
    }

    @Override
    public void delete(Long id) {
        filterRepository.delete(filterRepository.getById(id));
    }
}
