package com.example.demo.service;

import com.example.demo.common.Gender;
import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void addTeam(){

        Team team = Team.builder()
                .name("Chelsea Londyn")
                .nameOfCoach("Thomas")
                .surnameOfCoach("Tuchel")
                .budget(4000000)
                .build();

        Player player = Player.builder()
                .name("Didier")
                .surname("Drogba")
                .gender(Gender.MAN)
                .number(11)
                .build();


        player.setTeam(team);

        //Team result = teamRepository.save(team);
        playerRepository.save(player);

        //Collection<Team> resultCollection = teamRepository.findAllTeams();
    }
}
