package com.service_health_monitor_portal.simulator.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.service_health_monitor_portal.simulator.entity.Service;

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
        try {
            Service[] services = restTemplate.getForObject(url, Service[].class);
            System.out.println("Services fetched");
            System.out.println(Arrays.asList(services));
            return Arrays.asList(services);
        } catch (RestClientException e) {
            System.out.println("Error fetching services");
            return Arrays.asList();
        }
    }
}