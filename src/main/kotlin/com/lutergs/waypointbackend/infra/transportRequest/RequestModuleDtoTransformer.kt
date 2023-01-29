package com.lutergs.waypointbackend.infra.transportRequest

import com.lutergs.waypointbackend.domain.entity.*
import com.lutergs.waypointbackend.domain.entity.Route as Path
import com.lutergs.waypointbackend.domain.entity.Station as DomainStation


import java.lang.IllegalArgumentException
object RequestModuleDtoTransformer {

    fun toPath(requestModuleDto: RequestModuleDto): List<Path>{
        return requestModuleDto.result.path
            .map { apiPathResult ->
                Path(
                    type = this.pathTypeToRouteType(apiPathResult.pathType),
                    distance = apiPathResult.info.totalDistance,
                    duration = apiPathResult.info.totalTime,
                    routes = apiPathResult.subPath.map { this.subPathToSubRoute(it) }
                )
            }
    }

    private fun pathTypeToRouteType(pathType: Int): RouteType {
        return when(pathType) {
            1 -> RouteType.SUBWAY
            2 -> RouteType.BUS
            3 -> RouteType.BUS_SUBWAY
            else -> throw IllegalArgumentException("잘못된 경로 종류가 들어왔습니다.")
        }
    }

    private fun subPathToSubRoute(subPath: SubPath): SubRoute {
        return when(subPath.trafficType){
            1 -> SubwayRoute(
                subPath.distance,
                subPath.sectionTime,
                subPath.lane!!.map { it.name!! }.first(),
                subPath.passStopList!!.stations.map { this.dtoStationToDomainStation(it) }
            )
            2 -> BusRoute(
                subPath.distance,
                subPath.sectionTime,
                subPath.lane!!.map { it.busNo!! },
                subPath.passStopList!!.stations.map { this.dtoStationToDomainStation(it) }
            )
            3 -> WalkRoute(
                subPath.distance,
                subPath.sectionTime
            )
            else -> throw IllegalArgumentException("잘못된 경로 종류가 들어왔습니다.")
        }
    }

    private fun dtoStationToDomainStation(station: Station): DomainStation {
        return DomainStation(
            id = station.stationId,
            name = station.stationName,
            x = station.x.toDouble(),
            y = station.y.toDouble()
        )
    }
}