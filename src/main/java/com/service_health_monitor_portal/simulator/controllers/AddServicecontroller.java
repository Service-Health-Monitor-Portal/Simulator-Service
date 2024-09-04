package com.service_health_monitor_portal.simulator.controllers;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service_health_monitor_portal.simulator.Services.Log;
import com.service_health_monitor_portal.simulator.entity.Service;

@RestController()
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class AddServicecontroller {
    @Autowired
    private Log log;

    @PostMapping()
    public ResponseEntity<Map<String,Object>> createService(@RequestBody Service service) {
        System.out.println("Service added");
        System.out.println(service);
        log.generate(service);
        String id = service.getId().toString();
        JSONObject res = new JSONObject();
        res.put("id", id);
        return ResponseEntity.ok(res.toMap());
    }
}