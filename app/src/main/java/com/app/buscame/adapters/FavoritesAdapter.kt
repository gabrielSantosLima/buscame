package com.app.buscame.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.R
import com.app.buscame.dto.FavoriteDto
import com.app.buscame.features.favorites.FavoritesManagerJson
import com.app.buscame.features.redirectToPage.RedirectToPage
import com.app.buscame.features.share.ShareMessage
import com.app.buscame.utils.getSubstringOfText
import com.app.buscame.utils.toPriceFormat
import kotlinx.android.synthetic.main.list_view_favorites.view.*

class FavoritesAdapter(val fragment: Fragment) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>(){

    private val favoritesManagerJson = FavoritesManagerJson(fragment.requireContext().filesDir.path)
    private var favorites: List<FavoriteDto>

    init {
        favorites = favoritesManagerJson.list()
    }

    class FavoritesViewHolder(itemView: View, val favoritesManagerJson: FavoritesManagerJson, val fragment: Fragment) :
        RecyclerView.ViewHolder(itemView),
        CompoundButton.OnCheckedChangeListener,
            View.OnClickListener
    {
        private val MAX_DESCRIPTION_LENGTH = 33
        private val MAX_TITLE_LENGTH = 32

        init {
            itemView.bt_star.setOnCheckedChangeListener(this)
            itemView.bt_share.setOnClickListener(this)
            itemView.card.setOnClickListener{ openUrl(it) }
        }

        fun bindFavorite(favorite : FavoriteDto){
            with(favorite){
                itemView.txt_title.text = getSubstringOfText(product.title,MAX_TITLE_LENGTH)
                itemView.txt_description.text = getSubstringOfText(product.description,MAX_DESCRIPTION_LENGTH)
                itemView.txt_price.text = toPriceFormat(product.price!!)
                itemView.bt_star.tag = favorite
                itemView.bt_share.tag = favorite
                itemView.card.tag = favorite
            }
        }

        fun openUrl(v: View){
            val favorite = v.tag as FavoriteDto
            val redirectToPage = RedirectToPage(fragment)
            redirectToPage.redirect(favorite.product.url)
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            val favorite = buttonView?.tag as FavoriteDto
            if(!isChecked){
                buttonView?.setBackgroundResource(R.drawable.ic_star)
                removeFavorites(favorite.id!!)
            }
        }

        fun removeFavorites(id : String) {
            favoritesManagerJson.remove(id)
        }

        override fun onClick(v: View?) {
            val shareMessage = ShareMessage(fragment.requireContext(), "Compartilhar produto")
            val favorite = v?.tag as FavoriteDto
            shareMessage.shareProduct(favorite.product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_favorites, parent, false)
        return FavoritesViewHolder(view, favoritesManagerJson, fragment)
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bindFavorite(favorites[position])
    }
}