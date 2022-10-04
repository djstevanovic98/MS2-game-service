package rs.edu.raf.msa.game.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;
import rs.edu.raf.msa.game.entity.Game;
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    public Game findByCode(String code);
}
