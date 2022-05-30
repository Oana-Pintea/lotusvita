package com.oanapintea.lotusvita.repository;

import com.oanapintea.lotusvita.entities.FilterLotus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // trebuie sa fie de tip entitate ceea ce salvezi
public interface FilterRepository extends JpaRepository<FilterLotus, Long> { // clasa de salvat si id-ul ei  (primary key)

    @Query("select f from FilterLotus f where upper(f.email) like upper(?1)")
    List<FilterLotus> findAllByEmail(String email);

}
