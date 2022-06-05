package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team editTeam(Team team){
        return this.teamRepository.save(team);
    }

    @Transactional
    public Team addTeam(Team team){
        return this.teamRepository.save(team);
    }

    public void deleteTeam(Long id){
        this.teamRepository.deleteById(id);
    }

    public List<Team> getAllTeams(){
        return this.teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id){
        return this.teamRepository.findById(id);
    }

    public Optional<Team> getTeamByName(String name){
        return this.teamRepository.findByName(name);
    }
}
