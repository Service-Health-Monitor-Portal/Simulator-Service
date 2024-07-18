package com.service_health_monitor_portal.simulator.ServiceTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.service_health_monitor_portal.simulator.*;

public class ServiceTests {
    @Test
    public void ServiceClass_CreateTwoServices_ReturnWithDifferentId() {
        Service service1 = new Service();
        Service service2 = new Service();

        Assertions.assertThat(service1).isNotEqualTo(service2);
        Assertions.assertThat(service1).isNotNull();
        Assertions.assertThat(service2).isNotNull();
        Assertions.assertThat(service1.getId()).isNotEqualTo(service2.getId());
    }

    @Test
    public void ServiceClass_CreateService_ReturnCorrectServiceName() {
        Service service = new Service();
        service.setName("Service1");

        Assertions.assertThat(service.getName()).isEqualTo("Service1");
    }

    @Test
    public void ServiceClass_ValidateService_DoNotThrowError() {
        Service service = new Service("Service1", 60, 10, 10, 10, 10);

        Assertions.assertThatCode(() -> service.validate()).doesNotThrowAnyException();
    }

    @Test
    public void ServiceClass_ValidateService_ThrowError() {
        Service service = new Service("Service1", 1, 1, 1, 1, 1);

        Assertions.assertThatThrownBy(() -> service.validate()).isInstanceOf(IllegalArgumentException.class);
    }
}
