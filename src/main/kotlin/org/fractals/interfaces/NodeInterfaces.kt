package org.fractals.interfaces

import javafx.scene.Node

interface UINode<T : Node> {
    fun build(): T
}
