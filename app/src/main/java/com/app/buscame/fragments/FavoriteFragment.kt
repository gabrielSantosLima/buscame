package com.app.buscame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.adapters.FavoritesAdapter
import com.app.buscame.R
import com.app.buscame.features.favorites.FavoritesManagerJson
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.list_view_products.*

class FavoriteFragment : Fragment() {

    private lateinit var favoritesManagerJson : FavoritesManagerJson

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesManagerJson = FavoritesManagerJson(context?.applicationContext?.filesDir?.path!!)
        initRecycleView()
    }


    private fun initRecycleView(){
        bt_star.setOnCheckedChangeListener { buttonView, isChecked ->
            setAdapterOnRecycleView(FavoritesAdapter(context?.applicationContext!!))
        }
    }

    private fun setAdapterOnRecycleView(favoriteAdapter: FavoritesAdapter){
        list_favorites.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context?.applicationContext!!)
            adapter = favoriteAdapter
        }
    }
}