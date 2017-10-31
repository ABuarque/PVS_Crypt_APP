package com.ufal.rsawork.controllers

import com.jfoenix.controls.JFXTextField
import com.ufal.rsawork.Main
import com.ufal.rsawork.math.extendedEuclides
import com.ufal.rsawork.math.fastModularExponentiation
import com.ufal.rsawork.math.totient
import com.ufal.rsawork.persistence.saveDesencripted
import com.ufal.rsawork.utils.AlertDialog
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage


class DesencryptScreeController {

    @FXML
    private lateinit var pValue: JFXTextField

    @FXML
    private lateinit var qValue: JFXTextField

    @FXML internal lateinit var eValue: JFXTextField

    @FXML
    private lateinit var desencryptMessage: TextField

    @FXML
    fun desencryptAction(event: Event) {
        val p = pValue!!.text
        val q = qValue!!.text
        val e = eValue!!.text
        val encripted = desencryptMessage!!.text
        if (p == "" || q == "" || e == "" || encripted == "")
            AlertDialog.display("Aviso", "Por favor, preencha todos os campos!")
        else {
            val pValue = (p).toLong()
            val qValue = (q).toLong()
            val eValue = (e).toLong()
            val ASCIIs = encripted.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val message = ArrayList<String>()
            for (encripedPart in ASCIIs) {
                val asLong = java.lang.Long.parseLong(encripedPart)
                val totient = totient(pValue.toLong(), qValue.toLong())
                val N = (qValue * pValue).toLong()
                val part = fastModularExponentiation(asLong, extendedEuclides(eValue.toLong(), totient), N)
                message.add(part.toString() + "")
            }
            val x = StringBuilder()
            for (c in message)
                x.append(Integer.parseInt(c).toChar())

            AlertDialog.display("Mensagem Desencriptada", x.toString() + "\nA mensagem desencriptada foi salva com sucesso!")
            saveDesencripted(x.toString())
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
