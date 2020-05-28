package org.fractals.interfaces

import org.fractals.models.FractalSettings
import org.fractals.models.Point

interface Fractal {
    fun name(): String
    fun formula(fractalSettings: FractalSettings, drawPoint: (point: Point) -> Unit)
}
