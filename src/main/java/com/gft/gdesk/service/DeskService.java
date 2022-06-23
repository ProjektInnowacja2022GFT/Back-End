package com.gft.gdesk.service;

import com.gft.gdesk.entity.Desk;
import com.gft.gdesk.repository.DeskRepository;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeskService {

    private final DeskRepository deskRepository;

    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }
    public List<Desk> getFreeDesks() {
        return deskRepository.findAllFreeDesks();
    }
}
