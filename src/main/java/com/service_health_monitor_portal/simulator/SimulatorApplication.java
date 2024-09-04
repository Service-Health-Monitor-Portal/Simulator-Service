package com.service_health_monitor_portal.simulator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.service_health_monitor_portal.simulator.Services.Log;
import com.service_health_monitor_portal.simulator.Services.ServiceClient;
import com.service_health_monitor_portal.simulator.entity.Service;

@SpringBootApplication
@EnableAsync
public class SimulatorApplication implements CommandLineRunner {

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private Log log;

    public static void main(String[] args) {
        System.out.println("Starting simulator");
        SpringApplication.run(SimulatorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        pollForNewServices();
    }

    private void pollForNewServices() {
        System.out.println("Polling for new services");
        List<Service> services = serviceClient.fetchAllServices();
        for (Service service : services) {
            System.out.println("Service addeddddddddddddddddddddd");
            System.out.println(service);
            log.generate(service);
        }
    }
}
