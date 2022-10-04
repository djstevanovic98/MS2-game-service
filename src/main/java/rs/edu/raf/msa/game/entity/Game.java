package rs.edu.raf.msa.game.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Game {
	// TODO Also extend to save currently parsed status

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String code;
	@Column(nullable = false)
	String homeTeam;
	@Column(nullable = false)
	String visitorTeam;
	@Column
	LocalTime startTime;
	@Column
	LocalTime endTime;
	@Column
	boolean finished;

	@OneToMany(mappedBy = "game",targetEntity = Player.class,cascade = CascadeType.ALL)
	private List<Player> players = new ArrayList<>();

	@OneToMany(mappedBy = "game", targetEntity = Play.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Play> plays = new ArrayList<>();

	@Builder
	public Game(String code){
		this.code=code;
		this.homeTeam = code.substring(8,11);
		this.visitorTeam = code.substring(11,14);
	}

	public Game() {

	}
}
