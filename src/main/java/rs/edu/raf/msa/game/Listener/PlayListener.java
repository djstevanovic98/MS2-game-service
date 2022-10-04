package rs.edu.raf.msa.game.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.service.SendService;

import javax.persistence.PostPersist;

@Component
public class PlayListener {
    @Autowired
    SendService sendService;

    @PostPersist
    private void afterAnyUpdate(Play play){
        sendService.sendPlay(play);
    }
}
