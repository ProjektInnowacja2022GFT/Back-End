package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Desks;
import com.gft.gdesk.service.DesksService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/desk")
@RestController

@Getter
@Setter
public class DesksController {
    private DesksService deskService;

    public DesksController(DesksService deskService){
        this.deskService = deskService;
    }

    @GetMapping("/getDesks")
    public List<Desks> getDesks() {
        return deskService.getAllDesks();
    }
}
