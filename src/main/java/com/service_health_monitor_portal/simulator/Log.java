package com.service_health_monitor_portal.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Log {
    private final int Time = 6 * 1000;

    @Async
    public void generate(Service service) {
        Logger logger = LoggerFactory.getLogger(Log.class);
        List<String> serviceStates = List.of("success", "throttlingError", "dependencyError", "faultError", "invalidInputError");
        List<Integer> randoms = generateRandoms(service);
        try {
            while (true) {
                Random rnd = new Random();
                String currentState = serviceStates.get(randoms.get(rnd.nextInt(100)));
                ServiceHealth health = new ServiceHealth(service.getId().toString(), service.getName(), currentState);
                logger.atInfo().addKeyValue("service_log", health).log("Service health log");
                Thread.sleep(Time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> generateRandoms(Service service) {
        List<Integer> randoms = new ArrayList<>();
        addErrorCount(randoms, service.getSuccess(), 0);
        addErrorCount(randoms, service.getThrottlingError(), 1);
        addErrorCount(randoms, service.getDependencyError(), 2);
        addErrorCount(randoms, service.getFaultError(), 3);
        addErrorCount(randoms, service.getInvalidInputError(), 4);
        return randoms;
    }

    private void addErrorCount(List<Integer> randoms, int count, int errorIndex) {
        for (int i = 0; i < count; i++) {
            randoms.add(errorIndex);
        }
    }

    public record ServiceHealth(
        String id,
        String name,
        String status
    ) {
    }
}
