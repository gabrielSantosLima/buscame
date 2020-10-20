package com.app.buscame.utils

import java.util.*

private const val MAX_LENGTH = 10

fun randomNumberHex() : String {

    val random = Random()
    val randomNumber = random.nextInt()
    val hex = Integer.toHexString(randomNumber)

    if(hex.length < MAX_LENGTH) return randomNumberHex()

    return hex.substring(0, MAX_LENGTH)
}