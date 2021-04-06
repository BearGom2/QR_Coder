package com.example.qr_coder

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {



    private lateinit var button_main: Button
    private lateinit var editText_main: EditText
    private lateinit var imageView_main: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main =findViewById(R.id.button_main)
        editText_main =findViewById(R.id.editText_main)
        imageView_main =findViewById(R.id.imageView_main)

        button_main.setOnClickListener {
            val text = editText_main.text.toString()
            if (text.isNotBlank()) {
                val bitmap = generateQRCode(text)
                imageView_main.setImageBitmap(bitmap)
            }
        }
    }

    private fun generateQRCode(text: String): Bitmap {
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) { Log.d(TAG, "generateQRCode: ${e.message}") }
        return bitmap
    }
}