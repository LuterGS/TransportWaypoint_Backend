package com.lutergs.waypointbackend.domain.entity


data class Route (
    val type: RouteType,
    val distance: Double,
    val duration: Double,
    val routes: List<SubRoute>
)

abstract class SubRoute (
    open val distance: Double,
    open val duration: Int
)

data class WalkRoute(
    override val distance: Double,
    override val duration: Int
): SubRoute(distance, duration)

data class BusRoute(
    override val distance: Double,
    override val duration: Int,
    val lane: List<String>,
    val stations: List<Station>
): SubRoute(distance, duration)


data class SubwayRoute(
    override val distance: Double,
    override val duration: Int,
    val lane: String,
    val stations: List<Station>
): SubRoute(distance, duration)


data class Station(
    val id: Int,
    val name: String,
    val x: Double,
    val y: Double
)


enum class RouteType { BUS, SUBWAY, BUS_SUBWAY }
enum class SubRouteType { WALK, BUS, SUBWAY }
