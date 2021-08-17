package io.mendes.ping.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PingMetrics {
    private final MeterRegistry meterRegistry;

    private Counter pings;
    private Counter pongs;

    public PingMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        this.pings = Counter.builder("pings")
                .tag("type", "pings")
                .description("The number of ping received")
                .register(meterRegistry);

        this.pongs = Counter.builder("pongs")
                .tag("type", "pongs")
                .description("The number of pong received")
                .register(meterRegistry);
    }

    public Counter getPings() {
        return pings;
    }

    public Counter getPongs() {
        return pongs;
    }
}
