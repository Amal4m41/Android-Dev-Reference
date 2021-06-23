package com.example.cameraimageselectiondemo

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    companion object{
        private const val CAMERA_PERMISSION_CODE=1
        private const val CAMERA_REQUEST_CODE=2 // for intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val cameraButton:Button = findViewById<Button>(R.id.CameraButton)
        cameraButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
                ==PackageManager.PERMISSION_GRANTED){//if permission is granted

                startCameraActivity()
            }
            else{
                //request for permission
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //when permission is granted
                startCameraActivity()
                //Toast.makeText(this, "YES! GRANTED", Toast.LENGTH_SHORT).show()
            }
            else{
                //when permission is denied
                Toast.makeText(this, "Oops Camera permission was denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CAMERA_REQUEST_CODE){
            if(resultCode==Activity.RESULT_OK){
                val image:Bitmap=data!!.extras!!.get("data") as Bitmap
                val imageView:ImageView=findViewById(R.id.displayImageView)
                imageView.setImageBitmap(image)
            }
        }
    }

    private fun startCameraActivity(){
        //preparing intent to start camera
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }
}

