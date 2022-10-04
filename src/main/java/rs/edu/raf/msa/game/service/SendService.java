package rs.edu.raf.msa.game.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.entity.PlayPlayer;

@Service
public class SendService {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Autowired
    RabbitTemplate template;

    public void sendPlay(Play play){
        template.convertAndSend(queueName, PlayPlayer.builder()
                .gameNumber(play.getGameNumber())
                .description(play.getDescription())
                .homeScore(play.getHomeScore())
                .visitorScore(play.getVisitorScore())
                .quarter(play.getQuarter())
                .quarterTime(play.getQuarterTime())
                .build());
    }
}
