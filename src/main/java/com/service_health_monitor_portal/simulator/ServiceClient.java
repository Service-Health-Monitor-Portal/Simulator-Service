package com.service_health_monitor_portal.simulator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceClient {

    @Value("${log.analyzer.url}")
    private String logAnalyzerUrl;

    private final RestTemplate restTemplate;

    public ServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Service> fetchAllServices() {
        String url = logAnalyzerUrl + "/api/services/all";
        Service[] services = restTemplate.getForObject(url, Service[].class);
        System.out.println(Arrays.asList(services));
        System.out.println("Services fetched");
        return Arrays.asList(services);
    }
}