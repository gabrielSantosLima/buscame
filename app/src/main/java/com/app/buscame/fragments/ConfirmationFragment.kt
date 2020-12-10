package com.app.buscame.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.app.buscame.R
import kotlinx.android.synthetic.main.activity_main_opened.*
import kotlinx.android.synthetic.main.fragment_confirmation.*
import java.io.File

class ConfirmationFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        bt_close.setOnClickListener(this)
        setImage()
    }

    fun setImage(){
        val fileImage = getImageFromArguments()
        img.setImageURI(Uri.fromFile(fileImage))
    }

    fun getImageFromArguments() : File {
        val image = arguments?.get("image") as ByteArray
        val fileImage = createTempFile()
        fileImage.writeBytes(image)
        return fileImage
    }

    override fun onClick(v: View?) {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_confirmationFragment_to_cameraFragment)
    }
}
