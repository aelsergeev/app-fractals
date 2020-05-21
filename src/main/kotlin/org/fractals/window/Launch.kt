package org.fractals.window

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Stage

class Launch : Application() {

    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return

        primaryStage.title = "Fractals"

        val btn = Button().apply {
            text = "Mandelbrot"
            onAction = EventHandler { println("Mandelbrot") }
        }

        val label = Label().apply {
            text = "Fractals"
        }

        val root = Group()
        root.children.add(btn)
        root.children.add(label)

        primaryStage.scene = Scene(root, 800.0, 500.0)
        primaryStage.show()
    }

}
