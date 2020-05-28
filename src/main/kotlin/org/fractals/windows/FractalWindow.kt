package org.fractals.windows

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import org.fractals.nodes.FractalNode

const val SCENE_WIDTH = 800.0
const val SCENE_HEIGHT = 500.0

class Launch : Application() {

    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return
        primaryStage.apply {
            title = "Fractals"

            scene = Scene(FractalNode().build(), SCENE_WIDTH, SCENE_HEIGHT)
            show()
        }
    }

}
