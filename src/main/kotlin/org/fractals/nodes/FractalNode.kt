package org.fractals.nodes

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.fractals.models.FractalSettings
import org.fractals.utils.CanvasUtils
import org.fractals.windows.SCENE_HEIGHT
import org.fractals.windows.SCENE_WIDTH
import java.lang.NumberFormatException

const val CANVAS_WIDTH = SCENE_WIDTH
const val CANVAS_HEIGHT = SCENE_HEIGHT - 50

class FractalNode {

    private lateinit var canvas: Canvas
    private var fractalSettings = FractalSettings(1000, 3, 250, CANVAS_WIDTH, CANVAS_HEIGHT)

    fun build(): Parent {
        canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)

        val hBox = HBox(
            drawIntField(fractalSettings::iteration::get, fractalSettings::iteration::set),
            drawIntField(fractalSettings::zoom::get, fractalSettings::zoom::set),
            drawIntField(fractalSettings::colorChange::get, fractalSettings::colorChange::set),
            MandelbrotFractalNode().draw(canvas, fractalSettings, this::drawButton)
        ).apply {
            alignment = Pos.CENTER
            padding = Insets(10.0)
            spacing = 10.0
        }

        return VBox(hBox, canvas)
    }

    private fun drawIntField(get: () -> Int, set: (value: Int) -> Unit): TextField = TextField().apply {
        text = get().toString()
        alignment = Pos.CENTER

        textProperty().addListener { _, _, newValue ->
            set(try { newValue.toInt() } catch (e: NumberFormatException) { 1000 })
        }
    }

    private fun drawButton(name: String, event: () -> Unit): Button = Button().apply {
        text = name
        onAction = EventHandler { CanvasUtils.cleanCanvas(canvas); event() }
    }

}
