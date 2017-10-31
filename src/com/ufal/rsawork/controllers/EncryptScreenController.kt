package com.ufal.rsawork.controllers

import com.jfoenix.controls.JFXTextField
import com.ufal.rsawork.Main
import com.ufal.rsawork.math.fastModularExponentiation
import com.ufal.rsawork.persistence.*
import com.ufal.rsawork.utils.AlertDialog
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class EncryptScreenController {

    @FXML
    private lateinit var nValue: JFXTextField

    @FXML
    private lateinit var eValue: JFXTextField

    @FXML
    private lateinit var encryptMessage: JFXTextField

    @FXML
    fun backAction(event: Event) {
        try {
            (event.source as Node).scene.window.hide()
            val mainSource = FXMLLoader.load<Parent>(javaClass
                    .getResource("../layouts/main_screen.fxml"))
            val mainStage = Stage()
            mainStage.title = Main.APP_NAME
            mainStage.scene = Scene(mainSource)
            mainStage.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    @FXML
    fun encryptAction(event: Event) {
        val givenN = nValue!!.text
        val givenE = eValue!!.text
        val givenMessage = encryptMessage!!.text

        if (givenN == "" || givenE == "" || givenMessage == "") {
            AlertDialog.display("Aviso", "Por favor, preencha todos os campos!")
        } else {
            val eValue = (givenE).toLong()
            val nValue = (givenN).toLong()
            val encryptedMessage = StringBuilder()
            val messageChars = givenMessage.toCharArray()
            val messageSize = messageChars.size
            var iterator = 0
            for (c in messageChars) {
                val ASCII = c.toInt()
                val encrypted = fastModularExponentiation(ASCII.toLong(), eValue.toLong(), nValue.toLong())
                println(encrypted)
                encryptedMessage.append(encrypted.toString())
                if ((iterator + 1) != messageSize)
                    encryptedMessage.append(" ")
                iterator++
            }
            AlertDialog.display("Mensagem Encriptada", encryptedMessage.toString() + "\nA mensagem encriptada foi salva com sucesso!")
            saveData(encryptedMessage.toString())
            try {
                (event.source as Node).scene.window.hide()
                val mainSource = FXMLLoader.load<Parent>(javaClass
                        .getResource("../layouts/main_screen.fxml"))
                val mainStage = Stage()
                mainStage.title = Main.APP_NAME
                mainStage.scene = Scene(mainSource)
                mainStage.show()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}