package com.example.qrcodegeneratorr

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var qrCode : ImageView
    private lateinit var etData : EditText
    private lateinit var generator : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrCode=findViewById(R.id.qrCode)
        etData=findViewById(R.id.etData)
        generator=findViewById(R.id.generator)

        generator.setOnClickListener{
            val data =etData.text.toString().trim()
            if(data.isEmpty()){
                Toast.makeText(this, "Enter some data ", Toast.LENGTH_SHORT).show()
            }else{
                val writer = QRCodeWriter()
                try{
                    val bitMatrix=writer.encode(data, BarcodeFormat.QR_CODE,512,512)
                    val width=bitMatrix.width
                    val height =bitMatrix.height
                    val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for(x in 0 until width){
                        for(y in 0 until height) bmp.setPixel(x,y,if(bitMatrix[x,y]){Color.BLACK} else{Color.WHITE})
                    }
                    qrCode.setImageBitmap(bmp)
                }catch(e: WriterException){
                    e.printStackTrace()
                }
            }
        }
    }
}