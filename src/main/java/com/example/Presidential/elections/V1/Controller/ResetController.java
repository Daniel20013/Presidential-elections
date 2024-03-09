package com.example.Presidential.elections.V1.Controller;

import com.example.Presidential.elections.V1.Services.CandidateService;
import com.example.Presidential.elections.V1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.context.event.EventListener;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class ResetController {
    @Autowired
    private UserService userService;
    @Autowired
    private CandidateService candidateService;

    public void resetPresidentialElections() {
        userService.resetUsers();
        candidateService.deleteCandidates();
    }

    public void theSecondRound() {
        candidateService.topTwoCandidates();
        userService.resetUsers();
    }

    int TIME_SECOND_ROUND = 172800000;
    @EventListener(ContextRefreshedEvent.class)
    private void startTimerSecondRound() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                theSecondRound();
            }
        };
        timer.schedule(timerTask, TIME_SECOND_ROUND);
    }

    int TIME_RESET = 777600000;
    @EventListener(ContextRefreshedEvent.class)
    private void startTimerReset() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                resetPresidentialElections();
            }
        };
        timer.schedule(timerTask, TIME_RESET);
    }
}
