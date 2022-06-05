package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.model.dto.TeamDto;
import com.example.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") long id) {
        Optional<Team> team = teamService.getTeamById(id);

        if (team.isPresent()) {
            return new ResponseEntity<>(team.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> addTeam(@RequestBody TeamDto teamDto) {
        Optional<Team> teamAlreadyExists = teamService.getTeamByName(teamDto.getName());

        if (teamAlreadyExists.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Team teamFromDto = Team.builder()
                .name(teamDto.getName())
                .nameOfCoach(teamDto.getNameOfCoach())
                .surnameOfCoach(teamDto.getSurnameOfCoach())
                .budget(teamDto.getBudget())
                .build();

        Team teamSaved = teamService.addTeam(teamFromDto);
        return new ResponseEntity<>(teamSaved, HttpStatus.CREATED);

    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") long id, @RequestBody TeamDto teamDto) {
        Optional<Team> teamData = teamService.getTeamById(id);

        if (teamData.isPresent()) {
            Team editedTeam = teamData.get();
            editedTeam.setName(teamDto.getName());
            editedTeam.setNameOfCoach(teamDto.getNameOfCoach());
            editedTeam.setSurnameOfCoach(teamDto.getSurnameOfCoach());
            editedTeam.setBudget(teamDto.getBudget());
            return new ResponseEntity<>(teamService.editTeam(editedTeam), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
