package com.app.buscame

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.buscame.dto.ProductDto
import com.app.buscame.utils.getSubstringOfText
import com.app.buscame.utils.toPriceFormat
import kotlinx.android.synthetic.main.list_view_products.view.*

class ProductAdapter(private val products : List<ProductDto>) :  RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val MAX_DESCRIPTION_LENGTH = 33
        private val MAX_TITLE_LENGTH = 32

        fun bindProduct(product: ProductDto){
            with(product){
                itemView.txt_title.text = getSubstringOfText(title,MAX_TITLE_LENGTH)
                itemView.txt_description.text = getSubstringOfText(description,MAX_DESCRIPTION_LENGTH)
                itemView.txt_price.text = toPriceFormat(price)

                if(image == null) return

                itemView.img_product.setImageURI(Uri.fromFile(image))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_view_products, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

}