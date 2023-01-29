package com.lutergs.waypointbackend.application

import com.lutergs.waypointbackend.domain.TransportApiRequester
import com.lutergs.waypointbackend.domain.entity.GeoLocation
import com.lutergs.waypointbackend.domain.entity.Route
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class TransportService(
    private val transportService: TransportApiRequester
) {
    fun getPaths(startGeo: GeoLocation, endGeo: GeoLocation): Flux<Route> {
        return this.transportService.getPaths(startGeo, endGeo)
    }
    
    fun getFastestPath(startGeo: GeoLocation, endGeo: GeoLocation): Mono<Route> {
        return this.transportService.getPaths(startGeo, endGeo)
            .reduce { t, u ->
                if(t.duration > u.duration) u
                else t
            }
    }
}