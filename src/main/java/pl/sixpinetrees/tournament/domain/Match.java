package pl.sixpinetrees.tournament.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by maciej on 25.12.16.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    protected String name;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Round> rounds;

    public Match() {
    }

    public Match(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Collection<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public Collection<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Collection<Round> rounds) {
        this.rounds = rounds;
    }
}
