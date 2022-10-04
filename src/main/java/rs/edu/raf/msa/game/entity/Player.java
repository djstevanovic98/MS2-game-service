package rs.edu.raf.msa.game.entity;

import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Builder;
import lombok.Data;
import rs.edu.raf.msa.game.client.dto.PlayerDto;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name="Player")
@Table(name="player")
public class Player {
	// TODO Fill me
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	Long id;

	@Column
	String fullName;
	@Column
	String firstName;
	@Column
	String lastName;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	@Builder
	public Player(PlayerDto playerDto, Game game){
		this.firstName = playerDto.getF();
		this.lastName = playerDto.getL();
		this.fullName = playerDto.getC();
		this.game = game;
	}

}
