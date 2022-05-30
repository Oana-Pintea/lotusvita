package com.oanapintea.lotusvita.controller;

import com.oanapintea.lotusvita.entities.FilterLotus;
import com.oanapintea.lotusvita.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FilterController {

    @Autowired
    FilterService filterService; // se tine logica aplicatiei

    // endpoints localhost:8080/filters/oanapintea@gmaill.com
    @GetMapping("/filters/{email}")
    public ModelAndView getFiltersData(@PathVariable String email) { // ia emailul din calea data

        List<FilterLotus> filterData = filterService.getAllFilters(email);

        // debugg
        for (FilterLotus filterDatum : filterData) {
            System.out.println(filterDatum);
        }
        // da-mi viewul de filter-view (html ul cu css)
        ModelAndView modelAndView = new ModelAndView("filter-view");
        // cand deschid viewul respectiv am o variabila numita filters unde tin filter data
        modelAndView.addObject("filters", filterData);
        // idem pt email
        modelAndView.addObject("email", email);
        // afisam asta
        return modelAndView;
    }

    @GetMapping("/addfilter/{email}")
    public ModelAndView addFilter(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView("filter-add");
        FilterLotus filterLotus = new FilterLotus();
        modelAndView.addObject("filter", filterLotus);
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @PostMapping("/saveFilter/{email}")
    public ModelAndView addNewFilter(@ModelAttribute FilterLotus filter, @PathVariable String email) {
        filterService.save(filter, email); // ii spunem logicii sa salveze asta
        return getFiltersData(email);
    }

    @GetMapping("updateFilter/{id}/{email}")
    public String updateFilter(@PathVariable String email, @PathVariable Long id) {
        filterService.update(id);
        return "redirect:/filters/" + email;
    }


    @GetMapping("deleteFilter/{id}/{email}")
    public String deleteFilter(@PathVariable String email, @PathVariable Long id) {
        filterService.delete(id);
        return "redirect:/filters/" + email;
    }
}
