package com.app.buscame.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.app.buscame.R
import com.app.buscame.dto.FavoriteDto
import com.app.buscame.dto.ProductDto
import com.app.buscame.features.favorites.FavoritesManagerJson
import com.app.buscame.features.redirectToPage.RedirectToPage
import com.app.buscame.features.share.ShareMessage
import com.app.buscame.utils.getSubstringOfText
import com.app.buscame.utils.toPriceFormat
import kotlinx.android.synthetic.main.list_view_products.view.bt_share
import kotlinx.android.synthetic.main.list_view_products.view.bt_star
import kotlinx.android.synthetic.main.list_view_products.view.card
import kotlinx.android.synthetic.main.list_view_products.view.img_product
import kotlinx.android.synthetic.main.list_view_products.view.txt_description
import kotlinx.android.synthetic.main.list_view_products.view.txt_price
import kotlinx.android.synthetic.main.list_view_products.view.txt_title
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.util.*

class ProductsAdapter(val products: List<ProductDto>, val fragment: Fragment) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    private val favoritesManagerJson = FavoritesManagerJson(fragment.requireContext().filesDir.path)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_products, parent, false)
        return ProductsViewHolder(view, favoritesManagerJson, fragment)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    class ProductsViewHolder(itemView: View, val favoritesManagerJson: FavoritesManagerJson,val fragment: Fragment) :
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

        fun bindProduct(product: ProductDto){
            with(product){
                itemView.txt_title.text = getSubstringOfText(title,MAX_TITLE_LENGTH)
                itemView.txt_description.text = getSubstringOfText(description,MAX_DESCRIPTION_LENGTH)
                itemView.txt_price.text = toPriceFormat(price!!)
                itemView.bt_star.tag = product
                itemView.bt_share.tag = product
                image ?: return
                itemView.img_product.load(image)
            }
        }

        private fun openUrl(v: View){
            val product = v.tag as ProductDto
            val redirectToPage = RedirectToPage(fragment)
            redirectToPage.redirect(product.url)
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

        override fun onClick(v: View?) {
            val shareMessage = ShareMessage(fragment.requireContext(), "Compartilhar produto")
            val product = v?.tag as ProductDto
            shareMessage.shareProduct(product)
        }
    }
}
