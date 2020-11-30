package com.app.buscame

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.dto.FavoriteDto
import com.app.buscame.dto.ProductDto
import com.app.buscame.features.favorites.FavoritesManagerJson
import com.app.buscame.utils.getSubstringOfText
import com.app.buscame.utils.toPriceFormat
import kotlinx.android.synthetic.main.list_view_products.view.*
import java.io.File

class FavoritesAdapter(applicationContext: Context) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>(){

    private val favoritesManagerJson = FavoritesManagerJson(applicationContext.filesDir.path)
    private var favorites: List<FavoriteDto>

    init {
        favorites = favoritesManagerJson.list()
    }

    class FavoritesViewHolder(itemView: View, val favoritesManagerJson: FavoritesManagerJson) :
        RecyclerView.ViewHolder(itemView),
        CompoundButton.OnCheckedChangeListener
    {
        private val MAX_DESCRIPTION_LENGTH = 33
        private val MAX_TITLE_LENGTH = 32

        init {
            itemView.bt_star.setOnCheckedChangeListener(this)
        }

        fun bindFavorite(favorite : FavoriteDto){
            with(favorite){
                itemView.txt_title.text = getSubstringOfText(product.title,MAX_TITLE_LENGTH)
                itemView.txt_description.text = getSubstringOfText(product.description,MAX_DESCRIPTION_LENGTH)
                itemView.txt_price.text = toPriceFormat(product.price!!)
                itemView.bt_star.tag = favorite
                if(product.image == null) return
                itemView.img_product.setImageURI(Uri.fromFile(product.image))
            }
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            val favorite = buttonView?.tag as FavoriteDto
            if(!isChecked){
                buttonView?.setBackgroundResource(R.drawable.ic_star)
                removeFavorites(favorite.id!!)
            }
        }

        fun removeFavorites(id : String) = favoritesManagerJson.remove(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_products, parent, false)
        return FavoritesViewHolder(view, favoritesManagerJson)
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bindFavorite(favorites[position])
    }
}