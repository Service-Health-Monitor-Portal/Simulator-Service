package com.service_health_monitor_portal.simulator;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Log {
    @Async
    public void generate(Service service) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(service.name() + ".log", true))) {
            while (true) {
                writer.println("{\"success\":" + service.success() + ",\"throttlingError\":" + service.throttlingError() + ",\"dependencyError\":" + service.dependencyError() + ",\"faultError\":" + service.faultError() + ",\"invalidInputError\":" + service.invalidInputError() + "},");
                writer.flush();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
