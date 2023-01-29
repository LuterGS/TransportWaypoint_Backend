package com.lutergs.waypointbackend.infra.spring

import com.lutergs.waypointbackend.application.TransportService
import com.lutergs.waypointbackend.domain.TransportApiRequester
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanInitializer {

    @Bean
    fun transportService(
        transportApiRequester : TransportApiRequester
    ): TransportService {
        return TransportService(transportApiRequester)
    }
}