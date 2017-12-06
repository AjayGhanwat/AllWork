package com.bridgelabz.firstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera.setOnClickListener {
            callCamera()
        }
    }

    fun callCamera() {
        val openCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (openCamera.resolveActivity(packageManager) != null){

            startActivityForResult(openCamera, 1)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){

            1 -> {

                if (resultCode == Activity.RESULT_OK && data != null){

                    Toast.makeText(this, "Nice", Toast.LENGTH_LONG).show()

                }
            }

            else -> {

                Toast.makeText(this, "UnKnown Error " + requestCode, Toast.LENGTH_LONG).show()

            }
        }
    }
}