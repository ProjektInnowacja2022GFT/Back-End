package com.gft.gdesk.service;


import com.gft.gdesk.dto.Desk;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeskService {

    private final List<Desk> desks = new ArrayList<>();

    public List<Desk> getAllDesks() {
        return desks;
    }

    public Desk getDesksById(int id) {
        if (id > desks.size()) {
            return null;
        }
        return desks.get(id);
    }

}
