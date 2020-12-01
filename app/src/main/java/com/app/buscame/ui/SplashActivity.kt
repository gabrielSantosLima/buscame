package com.app.buscame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.buscame.R
import com.app.buscame.dto.FavoriteDto
import com.app.buscame.dto.HistoryDto
import com.app.buscame.dto.ProductDto
import com.app.buscame.features.favorites.FavoritesManagerJson
import com.app.buscame.features.historic.HistoricManagerJson
import com.app.buscame.utils.randomNumberHex
import java.util.*

class SplashActivity : AppCompatActivity() {

    private val DURATION_SPLASH: Long = 3000
    private lateinit var favoritesManagerJson: FavoritesManagerJson
    private lateinit var historicManagerJson: HistoricManagerJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        favoritesManagerJson = FavoritesManagerJson(applicationContext.filesDir.path)
//        historicManagerJson = HistoricManagerJson(applicationContext.filesDir.path)
//
//        iniciaTeste()

        val handler = Handler()
        handler.postDelayed(Runnable { showMainActivity() }, DURATION_SPLASH)
    }

    fun iniciaTeste(){
        val favoriteDto = FavoriteDto(
            null,
            ProductDto(
                null,
                "Computador",
                "Computador super top",
                1444.0,
                "E um computador",
                "www.sarahlinda.com.br",
                null
            ),
            Date()
        )
        val historyDto = HistoryDto(
            randomNumberHex(),
            "computador",
            "www.gabriellegal.com",
            Date()
        )

        favoritesManagerJson.save(favoriteDto)
        historicManagerJson.save(historyDto)
    }


    private fun showMainActivity() {
        val intent = Intent(
            this@SplashActivity, MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
