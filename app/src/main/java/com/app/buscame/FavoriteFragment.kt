package com.app.buscame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.features.favorites.FavoritesManagerJson
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.list_view_products.*

class FavoriteFragment : Fragment() {

    private lateinit var favoritesManagerJson : FavoritesManagerJson

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoritesManagerJson = FavoritesManagerJson(requireContext().filesDir.path)
        initRecycleView()
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private fun initRecycleView(){
        bt_star.setOnCheckedChangeListener { buttonView, isChecked ->
            setAdapterOnRecycleView(FavoritesAdapter(requireContext()))
        }
    }

    private fun setAdapterOnRecycleView(favoriteAdapter: FavoritesAdapter){
        list_favorites.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }
    }
}