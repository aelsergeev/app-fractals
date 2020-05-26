package org.fractals.window

import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import kotlin.math.ln

const val SCENE_WIDTH = 800.0
const val SCENE_HEIGHT = 500.0
const val CANVAS_WIDTH = SCENE_WIDTH
const val CANVAS_HEIGHT = SCENE_HEIGHT - 50
const val ZOOM = 250

class Launch : Application() {

    private lateinit var canvas: Canvas

    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return
        primaryStage.apply {
            title = "Fractals"
            canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)

            val hBox = HBox(
                drawSunshineButton(canvas),
                drawMandelbrot(canvas, 1000)
            ).apply {
                alignment = Pos.CENTER
                padding = Insets(10.0)
                spacing = 10.0
            }

            scene = Scene(VBox(hBox, canvas), SCENE_WIDTH, SCENE_HEIGHT)
            show()
        }
    }

    private fun drawMandelbrot(canvas: Canvas, iterations: Int): Button = drawButton("Mandelbrot Set") {
        val map = hashMapOf<Int, Color>()
        for (ix in 0 until canvas.width.toInt()) {
            for (iy in 0 until canvas.height.toInt()) {
                var zx = 0.0
                var zy = 0.0
                val cX = (ix - canvas.width / 2 - 100) / ZOOM
                val cY = (iy - canvas.height / 2) / ZOOM
                var i = iterations
                while (i > 0 && zx * zx + zy * zy < 4.0) {
                    val tmp = zx * zx - zy * zy + cX
                    zy = 2.0 * zx * zy + cY
                    zx = tmp
                    i--
                }

                val color = map.getOrPut(i) {
                    if (i > iterations || i == 0) {
                        Color.BLACK
                    } else {
                        val c = 3 * ln(iterations - i.toDouble()) / ln(iterations - 1.0)

                        when {
                            c < 1 -> Color.rgb((255 * c).toInt(), 0, 0)
                            c < 2 -> Color.rgb(255, (255 * (c - 1)).toInt(), 0)
                            else -> Color.rgb(255, 255, (255 * (c - 2)).toInt())
                        }
                    }
                }


                drawPoint(ix.toDouble(), iy.toDouble(), color)
            }
        }
        println(map)
    }

    private fun drawSunshineButton(canvas: Canvas): Button = drawButton("Colors Sunshine") {
        canvas.graphicsContext2D.apply {
            lineWidth = 3.0

            (0..100).forEach { _ ->
                strokeLine(Math.random() * canvas.width, Math.random() * canvas.height, 0.0, 0.0)
                stroke = Color.color(Math.random(), Math.random(), Math.random())
            }
        }
    }

    private fun drawPoint(x: Double, y: Double, color: Color = Color.BLACK) {
        canvas.graphicsContext2D.apply {
            lineWidth = 3.0
            fillRect(x, y, 4.0, 4.0)
            fill = color
        }
    }

    private fun cleanCanvas() {
        canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
    }

    private fun drawButton(name: String, event: () -> Unit): Button = Button().apply {
        text = name
        onAction = EventHandler { cleanCanvas(); event() }
    }

}
