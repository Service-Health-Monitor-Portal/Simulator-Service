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
    @Async
    public void generate(Service service) {
        Logger logger = LoggerFactory.getLogger(Log.class);
        List<String> serviceStates = List.of("success", "throttlingError", "dependencyError", "faultError", "invalidInputError");
        List<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < service.getSuccess(); i++) {
            randoms.add(0);
        }
        for (int i = 0; i < service.getThrottlingError(); i++) {
            randoms.add(1);
        }
        for (int i = 0; i < service.getDependencyError(); i++) {
            randoms.add(2);
        }
        for (int i = 0; i < service.getFaultError(); i++) {
            randoms.add(3);
        }
        for (int i = 0; i < service.getInvalidInputError(); i++) {
            randoms.add(4);
        }
        try {
            while (true) {
                Random rnd = new Random();
                String currentState = serviceStates.get(randoms.get(rnd.nextInt(100)));
                Service health = new Service(service);
                switch (currentState) {
                    case "success":
                        health.setSuccess(1);
                        break;
                    case "throttlingError":
                        health.setThrottlingError(1);
                        break;
                    case "dependencyError":
                        health.setDependencyError(1);
                        break;
                    case "faultError":
                        health.setFaultError(1);
                        break;
                    case "invalidInputError":
                        health.setInvalidInputError(1);
                        break;
                }
                logger.atInfo().addKeyValue("service_log", health).log("Service health log");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
