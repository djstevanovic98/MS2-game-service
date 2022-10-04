package rs.edu.raf.msa.game.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;
import rs.edu.raf.msa.game.entity.Player;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Player findByFullNameAndGameId(String fullName, long gameId);

	Player findByFirstNameAndLastName(String firstName, String lastName);
	
}
