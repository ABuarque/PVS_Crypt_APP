package com.ufal.rsawork.controllers

import com.jfoenix.controls.JFXTextField
import com.ufal.rsawork.Main
import com.ufal.rsawork.math.*
import com.ufal.rsawork.persistence.*
import com.ufal.rsawork.utils.AlertDialog
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

class GenerateKeyController {

    @FXML
    private lateinit var eLabel: Label

    @FXML
    private lateinit var msgSugestion: Label

    @FXML
    private lateinit var eValue: JFXTextField

    @FXML
    private lateinit var getTextP: JFXTextField

    @FXML
    private lateinit var getTextQ: JFXTextField

    private var savedTotient: Long = 0

    private var savedN: Long = 0

    private var savedE: Long = 0

    fun initialize() {
        eLabel!!.isVisible = false
        eValue!!.isVisible = false
    }

    @FXML
    fun confirmAction(event: Event) {
        val givenQ = getTextQ!!.text
        val givenP = getTextP!!.text
        if (givenP == "" || givenQ == "")
            AlertDialog.display("Aviso", "Por favor, preencha todos os campos.")
        else {
            val pValue = java.lang.Long.parseLong(givenP)
            val qValue = java.lang.Long.parseLong(givenQ)

            val pIsPrime = isPrime(pValue)
            val qIsPrime = isPrime(qValue)

            if (!pIsPrime || !qIsPrime) {
                val baseMessage = "O(s) valor(es) que você digitou apresenta(am) o(s) seguinte(s) erro(s):\n"
                if (!pIsPrime && !qIsPrime)
                    AlertDialog.display("Aviso", "$baseMessage'p' não é um número primo, por favor digite novamente!\n'q' não é um número primo, por favor digite novamente!")
                else if (!pIsPrime)
                    AlertDialog.display("Aviso", baseMessage + "'p' não é um número primo, por favor digite novamente!")
                else if (!qIsPrime)
                    AlertDialog.display("Aviso", baseMessage + "'q' não é um número primo, por favor digite novamente!")
            } else {
                savedN = pValue * qValue
                println("VAC: " + savedN)
                if (savedN < 256)
                    AlertDialog.display("Aviso", "O número 'N' encontrado é menor que o tamanho de caracteres" + " da tabela ASCII (256), por favor digite dois números 'p' e 'q' tais que N = p * q seja maior que 256!")
                else {
                    savedTotient = totient(pValue, qValue)
                    val coPrimes = getCoprimes(savedTotient)
                    eLabel!!.isVisible = true
                    eValue!!.isVisible = true
                    msgSugestion!!.text = "Sugestao de valores para número e: " + coPrimes.toString()
                }
            }
        }
    }

    @FXML
    fun generateKeyAction(event: Event) {
        val e = eValue!!.text
        if (e == "")
            AlertDialog.display("Aviso", "Insira a chave e.")
        else {
            savedE = Integer.parseInt(e).toLong()
            if (isCoPrime(savedE, savedTotient)) {
                println("CHAVE s1: $savedN CHAVE 2: $eValue")
                AlertDialog.display("Chaves geradas\n", "As chaves públicas 'N' = $savedN e 'e' = $savedE foram salvas com sucesso!")
                saveKeys(savedN, savedE)
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

            } else {
                AlertDialog.display("Aviso", "Insira um valor válido para a chave e.")
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
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

}
