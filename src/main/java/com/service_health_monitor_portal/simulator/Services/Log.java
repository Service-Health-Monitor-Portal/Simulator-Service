package com.service_health_monitor_portal.simulator.Services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.service_health_monitor_portal.simulator.entity.Service;

import reactor.core.publisher.Flux;

@Component
public class Log {
    private final int Time = 6; // Time in seconds

    public void generate(Service service) {
        Logger logger = LoggerFactory.getLogger(Log.class);

        Flux.interval(Duration.ofSeconds(Time))
            .map(tick -> generateLogStatus())
            .map(currentState -> new ServiceHealth(service.getId().toString(), service.getName(), currentState))
            .doOnNext(health -> {
                System.out.println(health);
                logger.atInfo().addKeyValue("service_log", health).log("Service health log");
            })
            .subscribe();
    }

    private String generateLogStatus() {
        Random random = new Random();
        int successWeight = random.nextInt(31) + 40;
        int totalWeight = 100;

        List<String> states = new ArrayList<>();

        for (int i = 0; i < successWeight; i++) {
            states.add("success");
        }

        for (int i = 0; i < totalWeight - successWeight; i++) {
            String state = switch (random.nextInt(4)) {
                case 0 -> "throttlingError";
                case 1 -> "dependencyError";
                case 2 -> "faultError";
                default -> "invalidInputError";
            };
            states.add(state);
        }

        return states.get(random.nextInt(states.size()));
    }

    public record ServiceHealth(
        String id,
        String name,
        String status
    ) {
    }
}
