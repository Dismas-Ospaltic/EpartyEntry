package com.st11.epartyentry.screens

//import android.app.Activity
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.hoverable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.platform.LocalDensity
//import androidx.core.view.WindowInsetsCompat
//import androidx.core.view.WindowInsetsControllerCompat
//import com.st11.epartyentry.R
//import com.st11.epartyentry.utils.DynamicStatusBar
//import org.koin.androidx.compose.koinViewModel
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EventDetailScreen(navController: NavController, itemId: String?) {
//
//    val backgroundColor = colorResource(id = R.color.pink)
//
//    DynamicStatusBar(backgroundColor)  // ✅ Apply dynamic status bar settings
//
//
//    val interactionSource = remember { MutableInteractionSource() }
//    var isHovered by remember { mutableStateOf(false) }
//
//    val buttonColor = colorResource(id = R.color.teal_700) // Button color
//    val textColor = colorResource(id = R.color.white) // Text color
//
//
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("Event Details", color = Color.White) }, // - Item $itemId
//                navigationIcon = {
//                    IconButton(
//                        onClick = { navController.popBackStack() },
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(50))
//                            .background(if (isHovered) Color.Gray.copy(alpha = 0.3f) else Color.Transparent)
//                            .hoverable(interactionSource = interactionSource, enabled = true)
//                            .pointerInput(Unit) {
//                                detectTapGestures(
//                                    onPress = {
//                                        isHovered = true
//                                        tryAwaitRelease()
//                                        isHovered = false
//                                    }
//                                )
//                            }
//                    ) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    containerColor = colorResource(id = R.color.dark),
//                    navigationIconContentColor = Color.White,
//                    titleContentColor = Color.White
//                )
//            )
//        }
//
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .verticalScroll(rememberScrollState()) // ✅ Enable scrolling
//        ) {
//
////            Spacer(modifier = Modifier.height(10.dp))
////
////            Box(
////                modifier = Modifier.fillMaxSize(),
////                contentAlignment = Alignment.Center
////            ) {
////                Text(
////                    text = "No Data Available",
////                    fontSize = 18.sp,
////                    fontWeight = FontWeight.Bold,
////                    color = Color.Gray
////                )
////            }
//            Box(
//                modifier = Modifier
//                    .fillMaxSize() // ✅ Take full width and height
//                    .padding(paddingValues)
//                    .background(colorResource(id = R.color.dark))
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.party04),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.matchParentSize().fillMaxSize()
//                )
//
//                Box(
//                    modifier = Modifier
//                        .matchParentSize()
//                        .background(
//                            Brush.verticalGradient(
//                                colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
//                                startY = 0f
//                            )
//                        )
//                )
//
//
//
//
//
//
//
//
//            }
//
//
//
//        }
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun EventDetailScreenPreview() {
//    EventDetailScreen(navController = rememberNavController(), itemId = "233")
//}

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.st11.epartyentry.R
import com.st11.epartyentry.navigation.Screen
import com.st11.epartyentry.screens.components.InvitePopup
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.utils.QrScannerActivity
import com.st11.epartyentry.viewmodel.EventsViewModel
import com.st11.epartyentry.viewmodel.InviteeViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Phone
import compose.icons.fontawesomeicons.solid.Qrcode
import compose.icons.fontawesomeicons.solid.ShareAlt
import compose.icons.fontawesomeicons.solid.Users
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(navController: NavController, itemId: String?) {

    val backgroundColor = colorResource(id = R.color.pink)
    DynamicStatusBar(backgroundColor)

    val interactionSource = remember { MutableInteractionSource() }
    var isHovered by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) } // State to control popup visibility
    val context = LocalContext.current

    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val formattedTime = formatter.format(Date()) // e.g., "07:32 AM"


    val eventViewModel: EventsViewModel = koinViewModel()
    val eventDetail by eventViewModel.eventDetail.collectAsState()


    val inviteeViewModel: InviteeViewModel = koinViewModel()
    val invitee by inviteeViewModel.getAllInvitee(itemId.toString())
        .collectAsState(initial = emptyList())
    val inviteeCount by inviteeViewModel.getAllTotalInvitee(itemId.toString())
        .collectAsState(initial = 0)
    val inviteeCheckIn by inviteeViewModel.getAllInviteeCheckIn(itemId.toString())
        .collectAsState(initial = emptyList())

//    val inviteeExists by inviteeViewModel.inviteeExists.collectAsState()

    LaunchedEffect(Unit) {
        if (itemId != null) {
            eventViewModel.getEventById(itemId)
            inviteeViewModel.getAllInvitee(itemId)
            inviteeViewModel.getAllTotalInvitee(itemId)
            inviteeViewModel.getAllInviteeCheckIn(itemId)
        }
    }



    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val intent = Intent(context, QrScannerActivity::class.java)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Event Details", color = Color.White) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(if (isHovered) Color.Gray.copy(alpha = 0.3f) else Color.Transparent)
                            .hoverable(interactionSource = interactionSource)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        isHovered = true
                                        tryAwaitRelease()
                                        isHovered = false
                                    }
                                )
                            }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 🔻 Background Image
            Image(
                painter = painterResource(id = R.drawable.party03),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // 🔻 Overlay (optional)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
                            startY = 0f
                        )
                    )
            )

            // 🔻 Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))
                            )
                        )

                        .clickable {
                            val eventId = itemId // Replace this with your actual event ID
                            val inviteId = invitee.firstOrNull()?.inviteId
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                val intent = Intent(context, QrScannerActivity::class.java).apply {
                                    putExtra("event_id", eventId)
                                    putExtra("invite_id", inviteId)
                                }
                                context.startActivity(intent)
                            } else {
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }

//                        .clickable {
//                            if (ContextCompat.checkSelfPermission(
//                                    context,
//                                    Manifest.permission.CAMERA
//                                )
//                                == PackageManager.PERMISSION_GRANTED
//                            ) {
//                                val intent = Intent(context, QrScannerActivity::class.java)
//                                context.startActivity(intent)
//                            } else {
//                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
//                            }
//                        }
                        .padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Qrcode,
                            contentDescription = "Scan QR",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Scan QR to enter the event",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Card 1
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Event Type: ${eventDetail?.eventType}", color = Color.Black)
                        Text("Event Date: ${eventDetail?.eventDate}", color = Color.Black)
                        Text("Description: ${eventDetail?.description}", color = Color.Black)
                        Text("Venue: ${eventDetail?.venue}", color = Color.Black)
                        Text("Phone: ${eventDetail?.phone}", color = Color.Black)
                        Text("Host: ${eventDetail?.hostName}", color = Color.Black)
                    }


                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {

                    val icons = listOf(
//                        "Call" to FontAwesomeIcons.Solid.Phone,
                        "Share" to FontAwesomeIcons.Solid.ShareAlt,
                        "Edit" to FontAwesomeIcons.Solid.Users
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp)),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        icons.forEach { (label, icon) ->

                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(if (isHovered) Color.LightGray.copy(alpha = 0.2f) else Color.Transparent)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onPress = {
                                                isHovered = true
                                                tryAwaitRelease()
                                                isHovered = false
                                            },
                                            onTap = {
                                                when (label) {
//                                                    "Call" -> {
//                                                        // 📞 Example: Dial a phone number
//                                                        val intent = Intent(Intent.ACTION_DIAL).apply {
//                                                            data = Uri.parse("tel:0700000000")
//                                                        }
//                                                        context.startActivity(intent)
//                                                    }

                                                    "Share" -> {
                                                        // 📤 Share event text
                                                        val shareText = """
                                            You have been invited to an event by ${eventDetail?.hostName}.
                                            Event Type: ${eventDetail?.eventType}
                                            Event Date: ${eventDetail?.eventDate}
                                            Description: ${eventDetail?.description}
                                            Venue: ${eventDetail?.venue}
                                            Phone: ${eventDetail?.phone}
                                            Host: ${eventDetail?.hostName}
                                            "You will be Required to use your QR code as Entrance which can only be Used ONCE
                                            if you do not have the QR code Please install the Eparty Entry app and create your Identity
                                            using Your full name and phone number
                                            "
                                            "Please RSVP, Welcome All"
                                        """.trimIndent()

                                                        val intent =
                                                            Intent(Intent.ACTION_SEND).apply {
                                                                type = "text/plain"
                                                                putExtra(
                                                                    Intent.EXTRA_TEXT,
                                                                    shareText
                                                                )
                                                            }
                                                        context.startActivity(
                                                            Intent.createChooser(
                                                                intent,
                                                                "Share Event Details"
                                                            )
                                                        )
                                                    }

                                                    "Edit" -> {
                                                        // ✏️ Navigate or show toast
                                                        showDialog = true
                                                        Toast.makeText(
                                                            context,
                                                            "Invite Friends and family",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                            }
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card 3
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Check Ins",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )


                        // ✅ Show "No Data Available" if the list is empty initially or after filtering
                        if (inviteeCheckIn.isEmpty()) {
                            // No data available at the initial display
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Data Available",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )
                            }
                        } else {
                            for (index in inviteeCheckIn.indices) {

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable { },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = inviteeCheckIn[index].fullName)
                                            Text(text = inviteeCheckIn[index].phone)
                                            Text(text = inviteeCheckIn[index].checkInTime)
                                        }
                                    }
                                }

                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp)) // Extra space at the bottom
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Card 2
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Invitees",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )


                        // ✅ Show "No Data Available" if the list is empty initially or after filtering
                        if (invitee.isEmpty()) {
                            // No data available at the initial display
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Data Available",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )
                            }
                        } else {
                            for (index in invitee.indices) {

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable { },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = invitee[index].fullName)
                                            Text(text = invitee[index].phone)
                                            Text(text = invitee[index].checkInTime)
                                        }
                                    }
                                }

                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp)) // Extra space at the bottom
                }

            }
        }

        // Show the Payment Popup if showDialog is true
        if (showDialog) {
            InvitePopup(onDismiss = { showDialog = false }, itemId.toString())

        }
    }
}


@Preview(showBackground = true)
@Composable
fun EventDetailScreenPreview() {
    EventDetailScreen(navController = rememberNavController(), itemId = "2")
}