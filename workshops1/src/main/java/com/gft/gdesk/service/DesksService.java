package com.gft.gdesk.service;


import com.gft.gdesk.dto.Desks;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DesksService {

    private List<Desks> desks = new ArrayList<>();

    public List<Desks> getAllDesks() {
        return desks;
    }
    public Desks getDesksById(int id) {
        if (id > desks.size()) {
            return null;
        }
        return desks.get(id);
    }


    @PostConstruct
    public void setInitialUsers() {
        this.desks.addAll(Arrays.asList(
                Desks.builder().
                        id(0L).
                        sector("A").
                        deskNumber(11).
                        floor(1).
                        build(),
                Desks.builder().
                        id(1L).
                        sector("A").
                        deskNumber(21).
                        floor(1).
                        build(),
                Desks.builder().
                        id(2L).
                        sector("C").
                        deskNumber(37).
                        floor(2).
                        build()
                ));
    }
}
