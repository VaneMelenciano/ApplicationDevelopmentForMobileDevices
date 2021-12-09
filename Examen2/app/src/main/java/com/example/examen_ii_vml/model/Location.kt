package com.example.examen_ii_vml.model

import com.google.android.gms.maps.model.LatLng

data class Location(
    val lat: Double, val lng:Double
){
    fun toLatLng(): LatLng{
        return LatLng(lat, lng)
    }
}
