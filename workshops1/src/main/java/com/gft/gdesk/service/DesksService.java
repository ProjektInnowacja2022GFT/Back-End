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

    @PostConstruct
    public void setInitialUsers() {
        this.desks.addAll(Arrays.asList(
                Desks.builder().
                        id(0L).
                        sector("A").
                        desk_number(11).
                        floor(1).
                        build(),
                Desks.builder().
                        id(1L).
                        sector("A").
                        desk_number(21).
                        floor(1).
                        build(),
                Desks.builder().
                        id(2L).
                        sector("C").
                        desk_number(37).
                        floor(2).
                        build()
                ));
    }
}
