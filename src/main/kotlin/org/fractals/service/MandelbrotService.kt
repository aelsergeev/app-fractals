package org.fractals.service

import javafx.scene.paint.Color
import org.fractals.interfaces.Fractal
import org.fractals.models.FractalSettings
import org.fractals.models.Point
import kotlin.math.ln

class MandelbrotService : Fractal {
    override fun name(): String {
        return "Mandelbrot Set"
    }

    override fun formula(fractalSettings: FractalSettings, drawPoint: (point: Point) -> Unit) {
        val (iteration, colorChange, zoom, width, height) = fractalSettings
        val map = hashMapOf<Int, Color>()
        for (ix in 0 until width.toInt()) {
            for (iy in 0 until height.toInt()) {
                val cX = (ix - width / 2 - 100) / zoom
                val cY = (iy - height / 2) / zoom

                var x = 0.0
                var y = 0.0

                var i = iteration
                while (i > 0 && x * x + y * y < 4.0) {
                    val xx = x * x
                    val yy = y * y
                    val xy = x * y
                    x = xx - yy + cX
                    y = 2.0 * xy + cY
                    --i
                }

                val color = map.getOrPut(i) {
                    if (i > iteration || i == 0) {
                        Color.BLACK
                    } else {
                        val c = colorChange * ln(iteration - i.toDouble()) / ln(iteration - 1.0)

                        when {
                            c < 1 -> Color.rgb((255 * c).toInt(), 0, 0)
                            c < 2 -> Color.rgb(255, (255 * (c - 1)).toInt(), 0)
                            else -> Color.rgb(255, 255, (255 * (c - 2)).toInt())
                        }
                    }
                }

                drawPoint(Point(ix.toDouble(), iy.toDouble(), color))
            }
        }
        println(map)
    }
}
