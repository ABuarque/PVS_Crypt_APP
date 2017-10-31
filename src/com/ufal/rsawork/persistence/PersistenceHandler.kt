package com.ufal.rsawork.persistence

import java.io.File

class PersistenceHandler

val PERSISTENCE_DESENCRIPTED = "desencrypted_text.txt"
val PERSISTENCE_PATH: String = "encrypted_text.txt"
val KEYS_PATH = "public_keys.txt"
val PRIVATE_KEYS = "private_keys.txt"

fun saveData(message: String) = File(PERSISTENCE_PATH).writeText("Mensagem encriptada: $message")

fun saveKeys(n: Long, e: Long) = File(KEYS_PATH).writeText("n = $n  e = $e")

fun saveDesencripted(message: String) = File(PERSISTENCE_DESENCRIPTED).writeText("Mensagem desencriptada: $message")

fun savePrivateKeys(p: Long, q: Long) = File(PRIVATE_KEYS).writeText("p = $p  q = $q")