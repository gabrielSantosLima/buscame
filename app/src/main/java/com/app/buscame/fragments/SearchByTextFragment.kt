package com.app.buscame.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.R
import com.app.buscame.adapters.FavoritesAdapter
import com.app.buscame.adapters.ProductsAdapter
import com.app.buscame.dto.ProductDto
import com.app.buscame.services.ProductSearchFactory
import kotlinx.android.synthetic.main.fragment_search_by_text.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception

class SearchByTextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_by_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        bt_config.setOnClickListener { toConfig() }
        bt_brand.setOnClickListener { select(it as Button) }
        bt_price.setOnClickListener { select(it as Button) }
        bt_site.setOnClickListener { select(it as Button) }
        getProducts()
    }

    private fun select(v: Button){
        bt_site.setBackgroundResource(R.drawable.white_button)
        bt_site.setTextColor(Color.BLACK)
        bt_price.setBackgroundResource(R.drawable.white_button)
        bt_price.setTextColor(Color.BLACK)
        bt_brand.setBackgroundResource(R.drawable.white_button)
        bt_brand.setTextColor(Color.BLACK)
        v.setBackgroundResource(R.drawable.black_button)
        v.setTextColor(Color.WHITE)
    }

    private fun toConfig() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_searchByTextFragment_to_settingsFragment)
    }

    private fun getImageToByteArrayFromArguments() : ByteArray? {
        val image = arguments?.get("image") as File?
        return image?.readBytes()
    }

    private fun getProducts(){
        val image = getImageToByteArrayFromArguments()
        image ?: return
        GlobalScope.launch {
            loading_include.visibility = View.VISIBLE
            fetchProducts(image)
        }
    }

    private fun fetchProducts(image: ByteArray){
        val service = ProductSearchFactory.getService()
        val requestBody = RequestBody.create(MediaType.get("image/jpeg"), image)
        val response = service.searchByImage(requestBody)
        response.enqueue(object: Callback<List<ProductDto>> {

            override fun onResponse(
                call: Call<List<ProductDto>>,
                response: Response<List<ProductDto>>
            ) {
                val products = response.body()
                products ?: throw Exception("Erro na busca por produtos.")

                setAdapterOnRecycleView(ProductsAdapter(products, context?.applicationContext!!))
                loading_include.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<ProductDto>>, t: Throwable) {
                Toast.makeText(context, "Erro na busca por produtos! Tente novamente.", Toast.LENGTH_LONG)
                loading_include.visibility = View.INVISIBLE
            }
        })
    }

    private fun setAdapterOnRecycleView(productsAdapter: ProductsAdapter){
        list_products.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context?.applicationContext!!)
            adapter = productsAdapter
        }
    }
}