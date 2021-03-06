package pl.sixpinetrees.tournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sixpinetrees.tournament.domain.BracketPosition;
import pl.sixpinetrees.tournament.domain.Match;
import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;
import pl.sixpinetrees.tournament.repository.MatchRepository;
import pl.sixpinetrees.tournament.service.ResultRegistrationService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    MatchRepository matchRepository;

    ResultRegistrationService resultRegistrationService;

    @Autowired
    public MatchController(MatchRepository matchRepository, ResultRegistrationService resultRegistrationService) {
        this.matchRepository = matchRepository;
        this.resultRegistrationService = resultRegistrationService;
    }

    @GetMapping
    public Collection<Match> getMatches() {

        return matchRepository.findAll();
    }

    @GetMapping("competition/{competitionId}")
    public Map<BracketPosition, Match> getMatchesByCompetition(@PathVariable("competitionId") Long competitionId) {

        return matchRepository.findByCompetitionId(competitionId).stream().collect(Collectors.toMap(Match::getBracketPosition, v -> v));
    }


    @GetMapping("/{matchId}")
    public Match match(@PathVariable("matchId") Long matchId) {

        return matchRepository.findById(matchId).orElseThrow( () -> new NotFoundException("Match with id " + matchId + " not found") );
    }

    @PostMapping("/edit/{matchId}")
    public ResponseEntity<?> processMatchForm(@PathVariable("matchId") Long matchId, @Valid @RequestBody ResultRegistrationForm resultRegistrationForm) {

        Long id = resultRegistrationService.registerResults(matchId, resultRegistrationForm);
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(id, responseHeader, HttpStatus.OK);
    }

}
