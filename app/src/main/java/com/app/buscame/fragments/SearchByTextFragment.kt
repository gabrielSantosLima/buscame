package com.app.buscame.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.buscame.R
import com.app.buscame.adapters.ProductsAdapter
import com.app.buscame.dto.ProductDto
import com.app.buscame.services.ProductSearchFactory
import kotlinx.android.synthetic.main.fragment_search_by_text.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception

class SearchByTextFragment : Fragment() {

    private var filterOn: String = ""

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
        bt_brand.setOnClickListener { select(it as Button, "brandName") }
        bt_price.setOnClickListener { select(it as Button, "price") }
        bt_site.setOnClickListener { select(it as Button, "url") }
        getProductsByImage()
        ipt_search.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    getProductsByText()
                    return true
                }
                return false
            }
        })
    }

    private fun select(v: Button, nameFilter: String){
        ipt_filter.isEnabled = true
        ipt_filter.setBackgroundResource(R.drawable.search_input)
        bt_site.setBackgroundResource(R.drawable.white_button)
        bt_site.setTextColor(Color.BLACK)
        bt_price.setBackgroundResource(R.drawable.white_button)
        bt_price.setTextColor(Color.BLACK)
        bt_brand.setBackgroundResource(R.drawable.white_button)
        bt_brand.setTextColor(Color.BLACK)
        v.setBackgroundResource(R.drawable.black_button)
        v.setTextColor(Color.WHITE)
        filterOn = nameFilter
    }

    private fun toConfig() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_searchByTextFragment_to_settingsFragment)
    }

    private fun getImageToByteArrayFromArguments() : ByteArray? {
        val image = arguments?.get("image") as File?
        return image?.readBytes()
    }

    private fun getProductsByImage(){
        val image = getImageToByteArrayFromArguments()
        image ?: return
        loading_include.visibility = View.VISIBLE
        GlobalScope.launch {
            fetchProducts(image)
        }
    }

    private fun getProductsByText(){
        val text = ipt_search.text.toString()
        if(text.isEmpty()) return
        loading_include.visibility = View.VISIBLE
        GlobalScope.launch {
            fetchProducts(text)
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

                setAdapterOnRecycleView(ProductsAdapter(products, this@SearchByTextFragment))
                loading_include.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<ProductDto>>, t: Throwable) {
                Toast.makeText(context?.applicationContext, "Erro na busca por produtos! Tente novamente.", Toast.LENGTH_LONG)
                loading_include.visibility = View.INVISIBLE
            }
        })
    }

    private fun fetchProducts(text: String){
        val params = getParams()
        val service = ProductSearchFactory.getService()
        var response : Call<List<ProductDto>>
        if(params["brandName"] != null){
            response = service.searchByText(text, brandName = params["brandName"].toString())
        }else if(params["price"] != null){
            response = service.searchByText(text, price = params["price"].toString().replace(",",".").toDouble())
        }else if(params["url"] != null){
            response = service.searchByText(text, url = params["url"].toString())
        }else{
            response = service.searchByText(text)
        }
        response.enqueue(object: Callback<List<ProductDto>> {

            override fun onResponse(
                call: Call<List<ProductDto>>,
                response: Response<List<ProductDto>>
            ) {
                val products = response.body()
                products ?: throw Exception("Erro na busca por produtos.")

                setAdapterOnRecycleView(ProductsAdapter(products, this@SearchByTextFragment))
                loading_include.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<ProductDto>>, t: Throwable) {
                Toast.makeText(context?.applicationContext, "Erro na busca por produtos! Tente novamente.", Toast.LENGTH_LONG)
                loading_include.visibility = View.INVISIBLE
            }
        })
    }

    private fun setAdapterOnRecycleView(productsAdapter: ProductsAdapter){
        list_products.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchByTextFragment.requireContext())
            adapter = productsAdapter
        }
    }

    private fun getParams(): Map<String,Any>{
        return mapOf( filterOn to ipt_filter.text.toString());
    }
}