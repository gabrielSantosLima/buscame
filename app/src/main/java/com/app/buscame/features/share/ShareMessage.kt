package com.app.buscame.features.share

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.app.buscame.dto.ProductDto

/*
    val productDto = ProductDto(
            "ade34sdd4d",
            "computador",
            "Computador Muito Bom",
            123.00,
            "Computador vem com Windows 7, 350Gb de memória RAM (Sonho)",
            "www.mercadolivre.com",
            null
        )

        ShareMessage(this, "Teste")
            .shareProduct(productDto)
*/

class ShareMessage(private val appCompatActivity: AppCompatActivity, private val title: String) : IShareMessage{

    override fun shareMessage(text: String){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent =  Intent.createChooser(sendIntent, title)
        appCompatActivity.startActivity(shareIntent)
    }

    override fun shareProduct(productDto: ProductDto) {
        val text = "\uD83E\uDD33\uD83C\uDFFB *Compartilhado pelo app Buscame* \uD83D\uDCF7\n\n" +
                "Nome : *${productDto.title}*\n" +
                "Descrição : *${productDto.description}*\n" +
                "Preço : *R$${productDto.price.toString().replace(".",",")}*\n" +
                "Site : ${productDto.url}\n\n" +
                "\uD83D\uDCF2 _Baixe nosso aplicativo na Google Play!_"

        shareMessage(text)
    }
}