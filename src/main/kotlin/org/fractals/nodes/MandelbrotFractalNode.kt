package org.fractals.nodes

import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import org.fractals.models.FractalSettings
import org.fractals.service.MandelbrotService
import org.fractals.utils.CanvasUtils
import kotlin.reflect.KFunction2

class MandelbrotFractalNode {
    private val mandelbrot = MandelbrotService()

    fun draw(
        canvas: Canvas,
        fractalSettings: FractalSettings,
        button: KFunction2<String, () -> Unit, Button>
    ): Button = button(mandelbrot.name()) {
        mandelbrot.formula(fractalSettings) { point ->
            CanvasUtils.drawPoint(canvas, point)
        }
    }

}
