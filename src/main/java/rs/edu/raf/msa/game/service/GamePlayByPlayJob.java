package rs.edu.raf.msa.game.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.edu.raf.msa.game.client.GameClient;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;
import rs.edu.raf.msa.game.entity.Game;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.entity.Player;
import rs.edu.raf.msa.game.repository.GameRepository;
import rs.edu.raf.msa.game.repository.PlayRepository;
import rs.edu.raf.msa.game.repository.PlayerRepository;

@Service
@Slf4j
public class GamePlayByPlayJob {
	@Autowired
	GameClient gameClient;

	@Autowired
	GameRepository gameRepository;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	PlayRepository playRepository;

	static int brojac=0;

	static final DateTimeFormatter MMSS = DateTimeFormatter.ofPattern("mm:ss"); //mozda moram hh:mm
	
	@Scheduled(fixedDelay = 10_000)
	public void scanGames() {
		List<String> allGames = gameClient.games();
		log.info("Loaded games from pbp-service: {}", allGames);

		for(String gameCode: allGames){
			Game game = gameRepository.findByCode(gameCode);

			if(game == null){
				game = new Game(gameCode);

				game = gameRepository.save(game);
			}else if (game.isFinished()){
				log.info("Utakmica je vec parsirana");
				continue;
			}
			if(game.getEndTime() == null){
				log.info("Starting parsing game: {}", game);

				List<PlayerDto> playerDtos = gameClient.players(gameCode);
				if(game.getPlayers().isEmpty()) {
					saveAllPlayers(game, playerDtos); //treba da snimim igrace voditi racuna da li postoje
				}
				game.setEndTime(LocalTime.parse("00:00:00")); //da li je ovo h ili je m
				System.out.println(game.getEndTime());
				System.out.println("IZNAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD");
			}

			game.setStartTime(game.getEndTime());
			System.out.println("------------------------->" + game.getStartTime().format(MMSS));
			game.setEndTime(game.getStartTime().plusMinutes(12));
			System.out.println("------------------------->EJEJEJJEJEJ" + game.getEndTime().format(MMSS));
			log.info("Loading plays for game {}", game);
			List<PlayDto> playDtos = gameClient.plays(game.getCode(),
					game.getStartTime().format(MMSS), game.getEndTime().format(MMSS));

			if(game.getEndTime().format(MMSS).equals(LocalTime.parse("00:48:00").format(MMSS))) {
				game.setFinished(true);
			}

			saveQuarterPlays(game,playDtos);
			log.info("Plays are saved.");
			gameRepository.save(game);
		}

	}

	public void saveAllPlayers(Game game, List<PlayerDto> PlayerDtos){
		List<Player> players = new ArrayList<>();

		for(PlayerDto playerDto: PlayerDtos){
			players.add(new Player(playerDto, game));
		}

		for(Player player: players){

			if(playerRepository.findByFullNameAndGameId(player.getFullName(),game.getId())==null) {
				game.getPlayers().add(player);
			}

		}
		log.info("Saving players... Added to list");

	}

	public void saveQuarterPlays(Game game, List<PlayDto> playDtos){
		List<Play> plays = new ArrayList<>();
		System.out.println(gameRepository.findByCode(game.getCode()).getPlays().size());

		for(PlayDto playDto: playDtos){ //nabavi quarter
			plays.add(new Play(game.getId(),brojac/3,playDto.getC(),playDto.getAtin(),
					playDto.getD(),playDto.getHs(),playDto.getVs(),game));
		}

		for (Play play : plays) {
			game.getPlays().add(play);
		}

		brojac++;
		System.out.println("Plays velicina je: " + plays.size());

	}
	
}
