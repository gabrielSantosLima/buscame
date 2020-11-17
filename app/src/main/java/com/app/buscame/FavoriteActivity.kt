package com.app.buscame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
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
        bt_config.setOnClickListener {
            openNextActivity()
        }
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
    private fun openNextActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(applicationContext, R.anim.move_left, R.anim.move_left)
        ActivityCompat.startActivities(this, arrayOf(intent), activityOptionsCompat.toBundle())
    }

}