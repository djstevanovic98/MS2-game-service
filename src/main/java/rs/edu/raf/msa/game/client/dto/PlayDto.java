package rs.edu.raf.msa.game.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlayDto {

	// TODO Fill me
	Long id;
    int p;
    String c;
    String d;
    String t;
    String atin;

    List<String> players;

    int x; int y; int hs; int vs;
    String et;

    int q;
    String gt;
}
