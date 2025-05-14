package com.st11.epartyentry.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.st11.epartyentry.model.InviteeEntity
import com.st11.epartyentry.viewmodel.InviteeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class QrScannerActivity : ComponentActivity() {

    private val inviteeViewModel: InviteeViewModel by viewModel() // ✅ Fix here
//    val eventId = intent.getStringExtra("event_id")
//    val inviteId = intent.getStringExtra("invite_id")
private lateinit var eventId: String
    private var inviteId: String? = null

    private val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private val formattedTime : String = formatter.format(Date()) // e.g., "07:32 AM"


    private val currentDate = System.currentTimeMillis()
    private val formattedDate: String = formatDate(currentDate)



    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {


//            val returnIntent = Intent()
//            returnIntent.putExtra("scannedResult", result.contents)
//            setResult(Activity.RESULT_OK, returnIntent)

            CoroutineScope(Dispatchers.Main).launch {


                val exists = inviteeViewModel.checkInvitee(result.contents, eventId)
                if (exists) {
                    Toast.makeText(this@QrScannerActivity, "Invitee already Checked in", Toast.LENGTH_SHORT).show()
                } else {
                inviteeViewModel.updateCheckIn(
                    phone =  result.contents,
                    eventId = eventId,
                    checkInTime = formattedTime,
                    checkInDate = formattedDate
                )
            }

                val isInvited = inviteeViewModel.checkInviteeIfInvited(result.contents, eventId)
                if (!isInvited) {
                  Toast.makeText(this@QrScannerActivity, "This person is not invited to this Event", Toast.LENGTH_LONG).show()
                }

            }
            Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val inviteeViewModel: InviteeViewModel by viewModel()
        // ✅ Now it's safe to read intent extras
        eventId = intent.getStringExtra("event_id") ?: ""
        inviteId = intent.getStringExtra("invite_id")

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