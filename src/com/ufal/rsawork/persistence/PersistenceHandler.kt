package com.ufal.rsawork.persistence

import java.io.File

class PersistenceHandler

val PERSISTENCE_DESENCRIPTED = "desencrypted_text.txt"
val PERSISTENCE_PATH: String = "encrypted_text.txt"
val KEYS_PATH = "keys.txt"

fun saveData(message: String) = File(PERSISTENCE_PATH).writeText("Mensagem encriptada: $message")

fun saveKeys(N: Long, e: Long) = File(KEYS_PATH).writeText("n = $N  e = $e")

fun saveDesemcripted(message: String) = File(PERSISTENCE_DESENCRIPTED).writeText("Mensagem desencriptada: $message")
