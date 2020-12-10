package com.app.buscame.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toFile
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.app.buscame.R
import com.app.buscame.features.importImage.ImportImage
import com.camerakit.CameraKit
import com.camerakit.CameraKitView
import kotlinx.android.synthetic.main.activity_main_opened.*
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class CameraFragment : Fragment(), CameraKitView.ImageCallback {

    private lateinit var cameraKitView: CameraKitView
    private lateinit var importImage: ImportImage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        prepareCamera(camera)
        activity?.bottom_nav?.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ImportImage.REQUEST_CODE_IMPORT_IMAGE && data?.data != null){
            val inputStream = context?.contentResolver?.openInputStream(data?.data!!)
            val image = inputStream?.readBytes()
            val bundle = bundleOf("image" to image)
            openConfirmFragment(bundle)
        }
    }

    fun prepareCamera(camera: CameraKitView){
        cameraKitView = camera
        importImage = ImportImage(this)

        bt_face.setOnClickListener { toggleFace() }
        bt_flash.setOnClickListener { toggleFlash() }
        bt_take_picture.setOnClickListener { takePicture() }
        bt_import.setOnClickListener { openGallery() }
    }

    fun openGallery(){
        importImage.import()
    }

    fun toggleFlash(){
        val isFlashOn = cameraKitView.flash == CameraKit.FLASH_ON
        if(isFlashOn) {
            cameraKitView.flash = CameraKit.FLASH_OFF
            bt_flash.setBackgroundResource(R.drawable.ic_flash_off)
            return
        }
        bt_flash.setBackgroundResource(R.drawable.ic_flash_on)
        cameraKitView.flash = CameraKit.FLASH_ON
    }

    fun toggleFace(){
        cameraKitView.toggleFacing()
    }

    fun takePicture(){
        cameraKitView.captureImage(this)
    }

    override fun onStart() {
        super.onStart()
        cameraKitView.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraKitView.onResume()
    }

    override fun onPause() {
        super.onPause()
        cameraKitView.onPause()
    }

    override fun onStop() {
        super.onStop()
        cameraKitView.onStop()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onImage(cameraKitView: CameraKitView, bytes: ByteArray) {
        val bundle = bundleOf("image" to bytes)
        openConfirmFragment(bundle)
    }

    fun openConfirmFragment(bundle: Bundle){
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_cameraFragment_to_confirmationFragment, bundle)
    }
}
