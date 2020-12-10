package com.app.buscame.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.buscame.R
import com.app.buscame.dto.ProductDto
import com.app.buscame.services.ProductSearchFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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
        getProducts()
    }

    private fun getImageToByteArrayFromArguments() : ByteArray {
        val image = arguments?.get("image") as File
        return image.readBytes()
    }

    private fun getProducts(){
        val image = getImageToByteArrayFromArguments()
        GlobalScope.launch {
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
                val data = response.body()
                print(data)
            }

            override fun onFailure(call: Call<List<ProductDto>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }
}