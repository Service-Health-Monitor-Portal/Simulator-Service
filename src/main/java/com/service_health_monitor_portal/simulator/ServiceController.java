package com.service_health_monitor_portal.simulator;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
public class ServiceController {
    @Autowired
    private Log log;
    
    @PostMapping("/api/v1/services")
    public void createService(@RequestBody Service service) {
        log.generate(service);
    }
}
