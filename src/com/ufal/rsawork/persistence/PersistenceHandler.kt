package com.ufal.rsawork.persistence

import java.io.File

class PersistenceHandler

val PERSISTENCE_DESENCRIPTED = "dese.d"
val PERSISTENCE_PATH: String = "message.d"
val KEYS_PATH = "keys.d"

fun saveData(message: String) = File(PERSISTENCE_PATH).writeText(message)

fun saveKeys(N: Long, e: Long) = File(KEYS_PATH).writeText("N: $N, e:$e")

fun saveDesemcripted(message: String) = File(PERSISTENCE_DESENCRIPTED).writeText(message)
