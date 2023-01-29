package com.lutergs.waypointbackend.infra.transportRequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * ODSay 대중교통 조회 API 스펙 (2023.01.29 확인)
 * https://lab.odsay.com/guide/releaseReference#searchPubTransPathT
 * */

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestModuleDto (
    @JsonProperty("result")             val result: TransportApiResult  // 전체 결과 확장
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TransportApiResult (
    @JsonProperty("searchType")         val searchType: Int,            // 검색결과 구분 (도시내 0, 도시간직통 1, 도시간환승 2)
    @JsonProperty("outTrafficCheck")    val outTrafficCheck: Int,       // 도시간 직통 탐색결과 유무 (False 0, True 1)
    @JsonProperty("busCount")           val busCount: Int,              // 버스 결과 개수
    @JsonProperty("subwayCount")        val subwayCount: Int,           // 지하철 결과 개수
    @JsonProperty("subwayBusCount")     val subwayBusCount: Int,        // 버스 + 지하철 개수
    @JsonProperty("pointDistance")      val pointDistance: Double,      // 출발지와 도착지의 직선거리
    @JsonProperty("startRadius")        val startRadius: Int,           // 출발지 반경
    @JsonProperty("endRadius")          val endRadius: Int,             // 도착지 반경
    @JsonProperty("path")               val path: List<Path>            // 결과 리스트
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Path(
    @JsonProperty("pathType")           val pathType: Int,              // 결과 종류 (지하철 1, 버스 2, 버스 + 지하철 3)
    @JsonProperty("info")               val info: PathInfo,             // 요약정보 확장 노드
    @JsonProperty("subPath")            val subPath: List<SubPath>      // 이동 교통수단 확장 노드
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PathInfo(
    @JsonProperty("trafficDistance")    val trafficDistance: Double,    // 도보 제외 이동거리
    @JsonProperty("totalWalk")          val totalWalk: Double,          // 총 도보 이동거리
    @JsonProperty("totalTime")          val totalTime: Double,          // 총 소요시간
    @JsonProperty("payment")            val payment: Int,               // 총 요금 (성인 기준)
    @JsonProperty("busTransitCount")    val busTransitCount: Int,       // 버스 환승 횟수
    @JsonProperty("subwayTransitCount") val subwayTransitCount: Int,    // 지하철 환승 횟수
    @JsonProperty("mapObj")             val mapObj: String,             // 보간점 API 파라미터값
    @JsonProperty("firstStartStation")  val firstStartStation: String,  // 최초 출발역 / 정류장
    @JsonProperty("lastEndStation")     val lastEndStation: String,     // 최종 도착역 / 정류쟝
    @JsonProperty("totalStationCount")  val totalStationCount: Int,     // 총 정류장 합
    @JsonProperty("busStationCount")    val busStationCount: Int,       // 버스 정류장 합
    @JsonProperty("subwayStationCount") val subwayStationCount: Int,    // 지하철 정류장 합
    @JsonProperty("totalDistance")      val totalDistance: Double,      // 총 거리
    @JsonProperty("checkIntervalTime")  val checkIntervalTime: Int,     // 배차간격 체크 기준시각
    @JsonProperty("checkIntervalTimeOverYn")val checkIntervalTimeoutYn: String      // 배차간격 체크 기준시간을 초과하는 노선 여부
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SubPath(
    @JsonProperty("trafficType")        val trafficType: Int,           // 이동수단 종류 (지하철 1, 버스 2, 도보 3)
    @JsonProperty("distance")           val distance: Double,           // 이동거리
    @JsonProperty("sectionTime")        val sectionTime: Int,           // 이동 소요시간
    @JsonProperty("stationCount")       val stationCount: Int?,         // 이동하여 정차하는 정거장 수
    @JsonProperty("lane")               val lane: List<Lane>?,          // 교통수단 정보 확장노드 (노선을 타는 버스나 지하철 정보들)
    @JsonProperty("startName")          val startName: String?,         // 승차 정류장 or 역 명
    @JsonProperty("startX")             val startX: Double?,            // 승차 정류장 or 역 X 좌표
    @JsonProperty("startY")             val startY: Double?,            // 승차 정류장 or 역 Y 좌표
    @JsonProperty("endName")            val endName: String?,           // 하차 정류장 or 역 명
    @JsonProperty("endX")               val endX: Double?,              // 하차 정류장 or 역 Y 좌표
    @JsonProperty("endY")               val endY: Double?,              // 하차 정류장 or 역 X 좌표
    @JsonProperty("way")                val way: String?,               // 방면 정보 (지하철일 때만)
    @JsonProperty("wayCode")            val wayCode: Int?,              // 방면 정보 코드 (지하철일 때만) (상행 1, 하행 2)
    @JsonProperty("door")               val door: String?,              // 지하철 빠른 환승 위치
    @JsonProperty("startID")            val startID: Int?,              // 출발 정류장 or 역 코드
    @JsonProperty("startStationCityCode")       val startStationCityCode: Int?,
    @JsonProperty("startStationProviderCode")   val startStationProviderCode: Int?,
    @JsonProperty("startLocalStationID")        val startLocalStationID: String?,
    @JsonProperty("startArsID")         val startArsID: String?,
    @JsonProperty("endID")              val endID: String?,             // 도착 정류장 or 역 코드
    @JsonProperty("endStationCityCode") val endStationCityCode: Int?,
    @JsonProperty("endStationProviderCode")     val endStationProviderCode: Int?,
    @JsonProperty("endArsID")           val endArsID: String?,
    @JsonProperty("startExitNo")        val startExitNo: String?,       // 지하철 들어가는 출구번호 (없을수도 있음)
    @JsonProperty("startExitX")         val startExitX: Double?,        // 지하철 들어가는 출구 X 좌표
    @JsonProperty("startExitY")         val startExitY: Double?,        // 지하철 들어가는 출구 Y 좌표
    @JsonProperty("endExitNo")          val endExitNo: String?,         // 지하철 나오는 출구번호 (없을수도 있음)
    @JsonProperty("endExitX")           val endExitX: Double?,          // 지하철 나오는 출구 X 좌표
    @JsonProperty("endExitY")           val endExitY: Double?,          // 지하철 나오는 출구 Y 좌표
    @JsonProperty("passStopList")       val passStopList: PassStopList? // 노선이 거쳐가는 정류장 리스트
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Lane(            // TODO: 추후 버스 / 지하철에 따라 분리 필요
    @JsonProperty("name")               val name: String?,              // (지하철일 때만) 지하철 노선명
    @JsonProperty("busNo")              val busNo: String?,             // (버스일 때만) 버스 번호
    @JsonProperty("type")               val type: Int?,                 // (버스일 때만) 버스 타입
    @JsonProperty("busID")              val busID: Int?,                // (버스일 때만) 버스 코드
    @JsonProperty("busLocalBlID")       val busLocalBlID: String?,
    @JsonProperty("busCityCode")        val busCityCode: Int?,
    @JsonProperty("busProviderCode")    val busProviderCode: Int?,
    @JsonProperty("subwayCode")         val subwayCode: Int?,           // (지하철일 때만) 지하철 노선 번호
    @JsonProperty("subwayCityCode")     val subwayCityCode: Int?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PassStopList(
    @JsonProperty("stations")           val stations: List<Station>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Station(
    @JsonProperty("index")              val index: Int,                 // 경로의 정류장 순번
    @JsonProperty("stationID")          val stationId: Int,             // 정류장 ID
    @JsonProperty("stationName")        val stationName: String,        // 정류장 명칭
    @JsonProperty("stationCityCode")    val stationCityCode: Int?,
    @JsonProperty("stationProviderCode")val stationProviderCode: Int?,
    @JsonProperty("localStationID")     val localStationID: String?,
    @JsonProperty("arsID")              val arsID: String?,
    @JsonProperty("x")                  val x: String,                  // 정류장 X 좌표
    @JsonProperty("y")                  val y: String,                  // 정류장 Y 좌표
    @JsonProperty("isNonStop")          val isNonStop: String?          // 미정차 정류장 여부 ("Y"/"N")
)
