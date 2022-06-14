package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Desk;
import com.gft.gdesk.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/desk")
@RestController
@RequiredArgsConstructor
public class DeskController {

    private final DeskService deskService;

    @GetMapping("/all-desks")
    public List<Desk> getDesks() {
        return deskService.getAllDesks();
    }

    @GetMapping("/free-desks")
    public List<Desk> getFreeDesks() {
        return deskService.getFreeDesks();
    }
}
