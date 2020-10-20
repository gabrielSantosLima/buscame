package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.app.buscame.dto.ProductDto
import com.app.buscame.features.share.ShareMessage
import kotlinx.android.synthetic.main.tutorial_one.*
import java.io.File

class TutorialOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_one)

        btnAdvance.setOnClickListener {
//            openNextActivity()
            algo()
        }
    }

    private fun algo(){
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
    }

    private fun openNextActivity() {
        val intent = Intent(this, TutorialTwo::class.java)
        val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(applicationContext, R.anim.move_left, R.anim.move_left)
        ActivityCompat.startActivities(this, arrayOf(intent), activityOptionsCompat.toBundle())
    }

}