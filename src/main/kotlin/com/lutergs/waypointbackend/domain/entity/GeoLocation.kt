package com.lutergs.waypointbackend.domain.entity

import java.math.BigDecimal
import java.math.RoundingMode

class GeoLocation(
    x: Double, y: Double
){
    val x: BigDecimal
    val y: BigDecimal
    init {
        this.x = BigDecimal(x).setScale(7, RoundingMode.HALF_EVEN)
        this.y = BigDecimal(y).setScale(7, RoundingMode.HALF_EVEN)
    }
}