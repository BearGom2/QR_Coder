package com.example.qr_coder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator


private lateinit var readerBtn: Button
private lateinit var QRcreateBtn: Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readerBtn = findViewById(R.id.readerBtn)
        QRcreateBtn = findViewById(R.id.QRcreateBtn)

        readerBtn.setOnClickListener {
            IntentIntegrator(this).initiateScan()
        }
        QRcreateBtn.setOnClickListener {
            val intent = Intent(this, GenerateActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "값: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}