package org.fractals.utils

import javafx.scene.canvas.Canvas
import org.fractals.models.Point

class CanvasUtils {

    companion object {
        fun drawPoint(canvas: Canvas, point: Point) {
            canvas.graphicsContext2D.apply {
                lineWidth = 3.0
                fillRect(point.x, point.y, 4.0, 4.0)
                fill = point.color
            }
        }

        fun cleanCanvas(canvas: Canvas) {
            canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
        }
    }

}
