package org.fractals.models

import javafx.scene.paint.Color

data class Point(
    val x: Double,
    val y: Double,
    val color: Color = Color.BLACK
)

data class FractalSettings(
    var iteration: Int,
    var colorChange: Int,
    var zoom: Int,
    val width: Double,
    val height: Double
)
