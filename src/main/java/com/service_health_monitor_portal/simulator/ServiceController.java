package com.service_health_monitor_portal.simulator;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/api/v1/services")
@CrossOrigin(origins = "*")
public class ServiceController {
    @Autowired
    private Log log;

    @PostMapping()
    public ResponseEntity<Map<String,Object>> createService(@RequestBody Service service) {
        service.validate();
        log.generate(service);
        String id = service.getId().toString();
        JSONObject res = new JSONObject();
        res.put("id", id);
        return ResponseEntity.ok(res.toMap());
    }
}
