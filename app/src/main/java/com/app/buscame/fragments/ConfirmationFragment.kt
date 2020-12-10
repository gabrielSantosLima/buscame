package com.app.buscame.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.app.buscame.R
import kotlinx.android.synthetic.main.fragment_confirmation.*
import java.io.File

class ConfirmationFragment : Fragment() {

    private lateinit var image : File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        bt_close.setOnClickListener{ close() }
        bt_analyse.setOnClickListener{ analyse() }
        setImage()
    }

    fun setImage(){
        image = getImageFromArguments()
        img.setImageURI(Uri.fromFile(image))
    }

    fun getImageFromArguments() : File {
        val image = arguments?.get("image") as ByteArray
        val fileImage = createTempFile()
        fileImage.writeBytes(image)
        return fileImage
    }

    private fun close(){
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_confirmationFragment_to_cameraFragment)
    }

    private fun analyse(){
        val bundle = bundleOf("image" to image)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_confirmationFragment_to_searchByTextFragment, bundle)
    }
}
