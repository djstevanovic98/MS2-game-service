package rs.edu.raf.msa.game.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.game.client.dto.PlayerDto;
import rs.edu.raf.msa.game.entity.Game;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.entity.Player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
public class PlayerRepositoryTest {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PlayRepository playRepository;

	@Test
	void testAll() {
		Game g = new Game("20200924PARZVE");
		g = gameRepository.save(g);
		// TODO saving game
		log.info("Game iz baze: {}", gameRepository.findByCode("20200924PARZVE"));
		assertThat(gameRepository.findByCode("20200924PARZVE")).isEqualTo(g);

	}

	@Test
	void savePlayer() {
		// TODO saving player

		PlayerDto playerDto = new PlayerDto();
		playerDto.setC("djordje_stevanovic");
		playerDto.setF("Djordje");
		playerDto.setL("Stevanovic");
		Player player = new Player(playerDto,null);

		playerRepository.save(player);
		//mozda i koristim gejm
		log.info("Player iz baze: {}", playerRepository.findByFirstNameAndLastName("Djordje", "Stevanovic"));
		assertNotNull(playerRepository.findByFirstNameAndLastName("Djordje", "Stevanovic"));
		assertThat(playerRepository.findByFirstNameAndLastName("Djordje", "Stevanovic"))
				.isEqualTo(player);
	}

	@Test
	void savePlay() {
		// TODO saving play(s) from game
//		Game g = new Game("20200924PARZVE");
		Play play = new Play((long)1,1,"11:00","3:00","proba",20,20,null);
		playRepository.save(play);//mozda mora gamerepos.save

		assertNotNull(playRepository.findByDescription("proba"));
		assertThat(playRepository.findByDescription("proba")).isEqualTo(play);
		log.info("plaj nas je: {} | plaj iz baze je: {}" , play,playRepository.findByDescription("proba"));
	}

	@Test
	void findByFirstNameAndLastName() {
		// TODO finding player by id
		PlayerDto playerDto = new PlayerDto();
		playerDto.setC("djordje_stevanovic");
		playerDto.setF("Djordje");
		playerDto.setL("Stevanovic");
		Player player = new Player(playerDto,null);
//
		playerRepository.save(player);

		Player player1 = playerRepository.findByFirstNameAndLastName("Djordje", "Stevanovic");
		log.info("player: {}", player);

		assertNotNull(playerRepository.findByFirstNameAndLastName("Djordje", "Stevanovic"));
		assertThat(player).isEqualTo(player1);
	}
}
