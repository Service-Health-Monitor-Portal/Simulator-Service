package com.service_health_monitor_portal.simulator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SimulatorApplication {

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private Log log;


    public static void main(String[] args) {
		System.out.println("Starting simulator");
        SpringApplication.run(SimulatorApplication.class, args);
    }

    @Scheduled(fixedRate = 6000) // Poll every 60 seconds
    public void pollForNewServices() {
		System.out.println("Polling for new services");
        List<Service> services = serviceClient.fetchAllServices();
        for (Service service : services) {
			log.generate(service);
        }
    }
}
