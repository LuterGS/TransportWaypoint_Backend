package com.lutergs.waypointbackend.infra.apiService

import com.lutergs.waypointbackend.application.TransportService
import com.lutergs.waypointbackend.domain.entity.GeoLocation
import com.lutergs.waypointbackend.domain.entity.Route
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class ApiService(
    private val transportService: TransportService
) {

    @GetMapping("/get_route")
    fun getRoute(
        @RequestParam("start_x") startX: Double,
        @RequestParam("start_y") startY: Double,
        @RequestParam("end_x") endX: Double,
        @RequestParam("end_y") endY: Double,
    ): Flux<Route> {
        return this.transportService.getPaths(
            GeoLocation(startX, startY), GeoLocation(endX, endY)
        )
    }

    @GetMapping("/get_fastest_route")
    fun getFastestRoute(
        @RequestParam("start_x") startX: Double,
        @RequestParam("start_y") startY: Double,
        @RequestParam("end_x") endX: Double,
        @RequestParam("end_y") endY: Double,
    ): Mono<Route> {
        return this.transportService.getFastestPath(
            GeoLocation(startX, startY), GeoLocation(endX, endY)
        )
    }
}