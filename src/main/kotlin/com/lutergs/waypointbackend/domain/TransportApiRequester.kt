package com.lutergs.waypointbackend.domain

import com.lutergs.waypointbackend.domain.entity.GeoLocation
import com.lutergs.waypointbackend.domain.entity.Route
import reactor.core.publisher.Flux

interface TransportApiRequester {

    fun getPaths(startGeo: GeoLocation, endGeo: GeoLocation): Flux<Route>

}