package com.app.buscame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.adapters.FavoritesAdapter
import com.app.buscame.R
import com.app.buscame.features.favorites.FavoritesManagerJson
import kotlinx.android.synthetic.main.fragment_favorite.*

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
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        bt_config.setOnClickListener { toConfig() }
        favoritesManagerJson = FavoritesManagerJson(context?.applicationContext?.filesDir?.path!!)
        initRecycleView()
    }

    private fun toConfig() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_favoriteFragment_to_settingsFragment)
    }

    private fun initRecycleView(){
        setAdapterOnRecycleView(FavoritesAdapter(this))
    }

    private fun setAdapterOnRecycleView(favoriteAdapter: FavoritesAdapter){
        list_favorites.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context?.applicationContext!!)
            adapter = favoriteAdapter
        }
    }
}