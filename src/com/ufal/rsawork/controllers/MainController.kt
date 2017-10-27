package com.ufal.rsawork.controllers

import com.ufal.rsawork.Main
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class MainController {

    private var utilParent: Parent? = null
    private var utilStage: Stage? = null

    @FXML
    fun generateKeyAction(event: Event) {
        try {
            (event.source as Node).scene.window.hide()
            utilParent = FXMLLoader.load<Parent>(javaClass
                    .getResource("../layouts/generate_key.fxml"))
            utilStage = Stage()
            utilStage!!.title = Main.APP_NAME
            utilStage!!.isResizable = false
            utilStage!!.scene = Scene(utilParent!!)
            utilStage!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun encryptKeyAction(event: Event) {
        try {
            (event.source as Node).scene.window.hide()
            utilParent = FXMLLoader.load<Parent>(javaClass
                    .getResource("../layouts/encrypt_screen.fxml"))
            utilStage = Stage()
            utilStage!!.title = Main.APP_NAME
            utilStage!!.isResizable = false
            utilStage!!.scene = Scene(utilParent!!)
            utilStage!!.show()
        } catch (e: Exception) {

        }
    }

    @FXML
    fun desencryptKeyAction(event: Event) {
        try {
            (event.source as Node).scene.window.hide()
            utilParent = FXMLLoader.load<Parent>(javaClass
                    .getResource("../layouts/desencrypt_screen.fxml"))
            utilStage = Stage()
            utilStage!!.title = Main.APP_NAME
            utilStage!!.isResizable = false
            utilStage!!.scene = Scene(utilParent!!)
            utilStage!!.show()
        } catch (e: Exception) {

        }

    }
}
