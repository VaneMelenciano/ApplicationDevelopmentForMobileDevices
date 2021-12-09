package com.example.examen_ii_vml.io.response

import com.example.examen_ii_vml.model.Step

data class DistanceResponse(
    val distance: Int,
    val price: Float,
    val steps: List<Step>,
    val time: String
)
