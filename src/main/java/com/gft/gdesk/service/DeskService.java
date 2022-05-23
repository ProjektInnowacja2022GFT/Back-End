package com.gft.gdesk.service;


import com.gft.gdesk.dto.Desk;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeskService {

    private List<Desk> desks = new ArrayList<>();

    public List<Desk> getAllDesks() {
        return desks;
    }

    public Desk getDesksById(int id) {
        if (id > desks.size()) {
            return null;
        }
        return desks.get(id);
    }


    @PostConstruct
    public void setInitialUsers() {
        this.desks.addAll(Arrays.asList(
                Desk.builder().
                        id(0L).
                        sector("A").
                        desk_number(11).
                        floor(1).
                        build(),
                Desk.builder().
                        id(1L).
                        sector("A").
                        desk_number(21).
                        floor(1).
                        build(),
                Desk.builder().
                        id(2L).
                        sector("C").
                        desk_number(37).
                        floor(2).
                        build()
        ));
    }
}
