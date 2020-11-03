package com.app.buscame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.dto.ProductDto
import com.app.buscame.features.favorites.FavoritesManagerJson
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoritesManagerJson : FavoritesManagerJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoritesManagerJson = FavoritesManagerJson(applicationContext.filesDir.path)

        initRecycleView()
    }

    private fun initRecycleView(){
        val favorites = favoritesManagerJson.list()
        val products = favorites.map { it.product }

        setAdapterOnRecycleView(ProductAdapter(products))
    }

    private fun setAdapterOnRecycleView(productAdapter: ProductAdapter){
        list_products.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = productAdapter
        }
    }
}