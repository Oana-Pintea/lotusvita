package com.oanapintea.lotusvita.service;

import com.oanapintea.lotusvita.entities.FilterLotus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FilterServiceImplTest {

    @Autowired
    private FilterService filterService;



    @Test
    public void save() {
        filterService.save(new FilterLotus("nume", 3, LocalDate.now()), "oana@gmail.com");
        int size = filterService.getAllFilters("oana@gmail.com").size();
        assertEquals(8, size);
    }

}