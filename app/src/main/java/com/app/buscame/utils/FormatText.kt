package com.app.buscame.utils

fun getSubstringOfText(text: String, limit: Int): String {

    if(text.length >= limit){
        return text.substring(0, limit)
    }

    return text
}

fun toPriceFormat(price : Double) : String {
    return "R\$ ${price.toString().replace(".",",")}"
}