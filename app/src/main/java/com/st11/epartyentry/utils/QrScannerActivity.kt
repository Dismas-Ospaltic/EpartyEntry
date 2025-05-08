package com.st11.epartyentry.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


class QrScannerActivity : ComponentActivity() {

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_LONG).show()
            val returnIntent = Intent()
            returnIntent.putExtra("scannedResult", result.contents)
            setResult(Activity.RESULT_OK, returnIntent)
        } else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Scan a QR Code")
            setCameraId(0)
            setBeepEnabled(true)
            setOrientationLocked(false) // this keeps the scanner from rotating
        }

        barcodeLauncher.launch(options)
    }



}