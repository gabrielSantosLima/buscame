package com.app.buscame.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.buscame.R
import com.camerakit.CameraKit
import com.camerakit.CameraKitView
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File

class CameraFragment : Fragment(), View.OnClickListener, CameraKitView.ImageCallback {

    private lateinit var cameraKitView: CameraKitView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareCamera(camera)
    }

    fun prepareCamera(camera: CameraKitView){
        cameraKitView = camera
        bt_face.setOnClickListener { toggleFace() }
        bt_flash.setOnClickListener { toggleFlash() }
        bt_take_picture.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        cameraKitView.captureImage(this)
    }

    override fun onImage(cameraKitView: CameraKitView, bytes: ByteArray) {
        val file = File("image.jpeg").writeBytes(bytes)
        //TODO Chamada a API
    }
}
