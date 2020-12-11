package com.app.buscame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.R
import com.app.buscame.dto.FavoriteDto
import com.app.buscame.dto.ProductDto
import com.app.buscame.features.favorites.FavoritesManagerJson
import com.app.buscame.utils.getSubstringOfText
import com.app.buscame.utils.toPriceFormat
import kotlinx.android.synthetic.main.list_view_products.view.*
import java.util.*

class ProductsAdapter(val products: List<ProductDto>, val applicationContext: Context) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    private val favoritesManagerJson = FavoritesManagerJson(applicationContext.filesDir.path)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_products, parent, false)
        return ProductsViewHolder(view, favoritesManagerJson)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    class ProductsViewHolder(itemView: View, val favoritesManagerJson: FavoritesManagerJson) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener{
        private val MAX_DESCRIPTION_LENGTH = 33
        private val MAX_TITLE_LENGTH = 32

        init {
            itemView.bt_star.setOnCheckedChangeListener(this)
        }

        fun bindProduct(product: ProductDto){
            with(product){
                itemView.txt_title.text = getSubstringOfText(title,MAX_TITLE_LENGTH)
                itemView.txt_description.text = getSubstringOfText(description,MAX_DESCRIPTION_LENGTH)
                itemView.txt_price.text = toPriceFormat(price!!)
                itemView.bt_star.tag = product
            }
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            val product = buttonView?.tag as ProductDto
            if(!isChecked){
                buttonView?.setBackgroundResource(R.drawable.ic_yellow_star)
                addFavorites(product)
            }else{
                buttonView?.setBackgroundResource(R.drawable.ic_star)
                removeFavorites(product.id!!)
            }
        }

        private fun addFavorites(product: ProductDto){
            favoritesManagerJson.save(
                FavoriteDto(
                    product.id,
                    product,
                    Date()
            ))
        }

        private fun removeFavorites(id: String){
            favoritesManagerJson.remove(id)
        }
    }
}
