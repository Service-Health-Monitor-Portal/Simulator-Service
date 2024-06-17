package com.service_health_monitor_portal.simulator;

public record Service(
    String name,
    int success,
    int throttlingError,
    int dependencyError,
    int faultError,
    int invalidInputError
) {}