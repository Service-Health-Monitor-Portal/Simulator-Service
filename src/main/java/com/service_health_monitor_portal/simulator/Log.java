package com.service_health_monitor_portal.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Log {
    @Async
    public void generate(Service service) {
        Logger logger = LoggerFactory.getLogger(Log.class);
        try {
            while (true) {
                logger.atInfo().addKeyValue("service_log", service).log("Service health log");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
