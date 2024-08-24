package com.service_health_monitor_portal.simulator;

import lombok.Data;

@Data
public class Service {
    private final Long id;
    private String name;

    public Service(String name, int success, int throttlingError, int dependencyError, int faultError,
            int invalidInputError, Long id) {
        this.id = id;
        this.name = name;
    }

}