package rs.edu.raf.msa.game.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class PlayPlayer {

	// TODO Reference to player
    Long gameNumber;
    String description;
    int homeScore;
    int visitorScore;
    String quarterTime;
    int quarter;

}
