package rs.edu.raf.msa.game.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;
import rs.edu.raf.msa.game.entity.Play;
@Repository
public interface PlayRepository extends JpaRepository<Play, Long> {

	//
//	List<Play> findByGameId(Long id, Sort sort);
    List<Play> findByGameNumber(Long id);

    Play findByDescription(String description);
}
