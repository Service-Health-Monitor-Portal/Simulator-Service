package com.service_health_monitor_portal.simulator;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController()
@RequestMapping("/api/v1/services")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiceController {
    @Autowired
    private Log log;
    
    @PostMapping()
    public void createService(@RequestBody Service service) {
        service.validate();
        log.generate(service);
    }
}
