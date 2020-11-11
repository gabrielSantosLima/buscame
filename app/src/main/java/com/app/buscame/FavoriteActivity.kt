package com.app.buscame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.features.favorites.FavoritesManagerJson
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.list_view_products.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoritesManagerJson : FavoritesManagerJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        favoritesManagerJson = FavoritesManagerJson(applicationContext.filesDir.path)

        initRecycleView()
    }

    private fun initRecycleView(){
        bt_star.setOnCheckedChangeListener { buttonView, isChecked ->
            setAdapterOnRecycleView(FavoritesAdapter(applicationContext))
        }
    }

    private fun setAdapterOnRecycleView(favoriteAdapter: FavoritesAdapter){
        list_favorites.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = favoriteAdapter
        }
    }
}