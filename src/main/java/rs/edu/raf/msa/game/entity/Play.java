package rs.edu.raf.msa.game.entity;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import rs.edu.raf.msa.game.Listener.PlayListener;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@EntityListeners(PlayListener.class)
@Entity(name="Play")
@Table(name="play")
public class Play {
	// TODO Consider using @Builder

	@Id //namesti autoinkrement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	Long gameNumber;
	@Column
	int quarter;

	@Column
	String quarterTime;
	@Column
	String gameTime;
	@Column
	String description;
	@Column
	int homeScore;
	@Column
	int visitorScore;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	@Builder
	public Play(Long gameNumber, int quarter, String quarterTime, String gameTime,
				String description, int homeScore, int visitorScore, Game game){ // long... playerIds
		super();
		this.gameNumber=gameNumber;
		this.quarter=quarter;
		this.quarterTime = quarterTime;
		this.gameTime = gameTime;
		this.description = description;
		this.homeScore = homeScore;
		this.visitorScore = visitorScore;
//		this.addPlayers(playerIds);
		this.game = game;
	}


}
