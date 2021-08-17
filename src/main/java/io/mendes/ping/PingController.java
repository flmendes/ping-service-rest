package io.mendes.ping;

import io.mendes.ping.metrics.PingMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private final PingMetrics metrics;

    public PingController(PingMetrics metrics) {
        this.metrics = metrics;
    }

    @GetMapping("/ping")
    public String sayPong(){
        metrics.getPings().increment();
        return "Pong";
    }
}
