package com.ufal.rsawork.controllers

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import com.ufal.rsawork.Main
import com.ufal.rsawork.math.*
import com.ufal.rsawork.persistence.*
import com.ufal.rsawork.utils.AlertDialog
import com.ufal.rsawork.persistence.savePrivateKeys
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
    private lateinit var msgSugestionE: Label

    @FXML
    private lateinit var generateButton: JFXButton

    @FXML
    private lateinit var eValue: JFXTextField

    @FXML
    private lateinit var getTextP: JFXTextField

    @FXML
    private lateinit var getTextQ: JFXTextField

    private var savedTotient: Long = 0

    private var savedN: Long = 0

    private var savedP: Long = 0

    private var savedQ: Long = 0

    private var savedE: Long = 0

    fun initialize() {
        eLabel!!.isVisible = false
        eValue!!.isVisible = false
        generateButton!!.isVisible = false
    }

    @FXML
    fun confirmAction(event: Event) {
        val givenQ = getTextQ!!.text
        val givenP = getTextP!!.text
        if (givenP == "" || givenQ == "")
            AlertDialog.display("Aviso", "Por favor, preencha todos os campos!")
        else {
            val pValue = java.lang.Long.parseLong(givenP)
            val qValue = java.lang.Long.parseLong(givenQ)

            val pIsPrime = isPrime(pValue)
            val qIsPrime = isPrime(qValue)

            if(pValue == qValue) {
                AlertDialog.display("Aviso", "Por favor, digite dois valores diferentes para 'p' e 'q'!")
            } else {
                if (!pIsPrime || !qIsPrime) {
                    val baseMessage = "O(s) valor(es) que você digitou apresenta(am) o(s) seguinte(s) erro(s):\n"
                    if (!pIsPrime && !qIsPrime)
                        AlertDialog.display("Aviso", "$baseMessage'p' não é um número primo!\n'q' não é um número primo!")
                    else if (!pIsPrime)
                        AlertDialog.display("Aviso", baseMessage + "'p' não é um número primo!")
                    else if (!qIsPrime)
                        AlertDialog.display("Aviso", baseMessage + "'q' não é um número primo!")
                } else {
                    savedN = pValue * qValue
                    savedP = pValue
                    savedQ = qValue
                    println("p: " + savedP)
                    println("q: " + savedQ)
                    println("n: " + savedN)
                    if (savedN < 256)
                        AlertDialog.display("Aviso", "O número 'n' encontrado é menor que o tamanho de caracteres\nda tabela ASCII (256), por favor, digite dois números 'p' e 'q'\ntais que n = p * q seja maior que 256!")
                    else {
                        savedTotient = totient(pValue, qValue)
                        println("Totient: " + savedTotient)
                        val coPrimes = getCoprimes(savedTotient)
                        eLabel!!.isVisible = true
                        eValue!!.isVisible = true
                        msgSugestion!!.text = "Sugestões de números para o expoente 'e': " + coPrimes.toString()
                        msgSugestionE!!.text = "Digite um expoente 'e' coprimo a (p-1)(q-1):"
                        generateButton!!.isVisible = true
                    }
                }
            }
        }
    }

    @FXML
    fun generateKeyAction(event: Event) {
        val e = eValue!!.text
        if (e == "")
            AlertDialog.display("Aviso", "Por favor, preencha o campo referente ao valor do expoente 'e'!")
        else {
            savedE = Integer.parseInt(e).toLong()
            if (isCoPrime(savedE, savedTotient)) {
                println("e: " + savedE)
                AlertDialog.display("Chaves Públicas", "As chaves públicas 'n' = $savedN e 'e' = $savedE foram salvas com sucesso!")
                saveKeys(savedN, savedE)
                savePrivateKeys(savedP, savedQ)
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
                AlertDialog.display("Aviso", "Por favor, insira um valor válido para o expoente 'e'!")
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
