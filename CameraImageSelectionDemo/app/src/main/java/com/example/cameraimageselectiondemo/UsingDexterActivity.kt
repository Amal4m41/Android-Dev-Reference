package com.example.cameraimageselectiondemo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

//Same code but handling camera permissions using Dexter library
class UsingDexterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)   //showing the main activity layout file
        val button=findViewById<Button>(R.id.CameraButton)
        button.setOnClickListener {
            checkPermissionAndStartCamera()
        }
    }

    private fun checkPermissionAndStartCamera(){
        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    // permission is granted, open the camera
                    val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent,MainActivity.CAMERA_REQUEST_CODE)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    // check for permanent denial of permission
                    if (response.isPermanentlyDenied) {
                        Toast.makeText(this@UsingDexterActivity, "Oops looks like you have denied" +
                                " the permission! You can enable the same in Application Settings.", Toast.LENGTH_SHORT).show()
                    }else{
                        //just a simple deny
                        Toast.makeText(this@UsingDexterActivity, "Denied permission to start camera", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken) {
//                    Toast.makeText(this@UsingDexterActivity, "3", Toast.LENGTH_SHORT).show()
                    token.continuePermissionRequest()
                }
            }).check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==MainActivity.CAMERA_REQUEST_CODE){
                val imageFromCamera:Bitmap=data!!.extras!!.get("data") as Bitmap
                val imageView:ImageView=findViewById<ImageView>(R.id.displayImageView)
                imageView.setImageBitmap(imageFromCamera)
            }
        }
    }
}