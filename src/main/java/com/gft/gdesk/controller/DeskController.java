package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Desk;
import com.gft.gdesk.service.DeskService;
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
public class DeskController {
    private DeskService deskService;

    public DeskController(DeskService deskService){
        this.deskService = deskService;
    }

    @GetMapping("/getDesks")
    public List<Desk> getDesks() {
        return deskService.getAllDesks();
    }
}
