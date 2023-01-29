package com.lutergs.waypointbackend.infra.transportRequest

import com.lutergs.waypointbackend.domain.TransportApiRequester
import com.lutergs.waypointbackend.domain.entity.GeoLocation
import com.lutergs.waypointbackend.domain.entity.Route
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux



@Component
class RequestModule(
    @Value("\${custom.odsay-api-key}") private val odsayApiKey: String,
): TransportApiRequester {

    private val reactiveWebClient = WebClient.builder()
        .baseUrl("https://api.odsay.com")
        .build()
    private val TRANSPORT_API_PATH = "v1/api/searchPubTransPathT"


//    @PostConstruct
//    fun test(){
//        this.callTransportApi(
//            GeoLocation(126.9027279, 37.5349277),
//            GeoLocation(126.9145430, 37.5499421)
//        )
//    }

    override fun getPaths(startGeo: GeoLocation, endGeo: GeoLocation): Flux<Route> {
        // TODO : searchPathType 파라미터 추가 필요
        return this.reactiveWebClient.get()
            .uri {
                it.path(this.TRANSPORT_API_PATH)
                    .queryParam("apiKey", this.odsayApiKey)
                    .queryParam("SX", startGeo.x.toString())
                    .queryParam("SY", startGeo.y.toString())
                    .queryParam("EX", endGeo.x.toString())
                    .queryParam("EY", endGeo.y.toString())
                    .queryParam("SearchPathType", 0)
                    .build()
            }
            .headers {
                it["Content-type"] = "application/json"
            }
            .retrieve()
            .bodyToMono(RequestModuleDto::class.java)
            .map {
                RequestModuleDtoTransformer.toPath(it )
            }.flatMapMany { Flux.fromIterable(it) }
    }
}