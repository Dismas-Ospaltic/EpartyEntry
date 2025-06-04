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
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.st11.epartyentry.R
import com.st11.epartyentry.model.InviteeEntity
import com.st11.epartyentry.navigation.Screen
import com.st11.epartyentry.screens.components.EditInvitePopup
import com.st11.epartyentry.screens.components.InvitePopup
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.utils.QrScannerActivity
import com.st11.epartyentry.viewmodel.EventsViewModel
import com.st11.epartyentry.viewmodel.InviteeViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Edit
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Qrcode
import compose.icons.fontawesomeicons.solid.ShareAlt
import compose.icons.fontawesomeicons.solid.Trash
import compose.icons.fontawesomeicons.solid.Users
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EventDetailScreen(navController: NavController, itemId: String?) {

    val backgroundColor = colorResource(id = R.color.pink)
    DynamicStatusBar(backgroundColor)

    val interactionSource = remember { MutableInteractionSource() }
    var isHovered by remember { mutableStateOf(false) }
    var isHoveredShare by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) } // State to control popup visibility
    var showDialogEdit by remember { mutableStateOf(false) } // State to control popup visibility


    val context = LocalContext.current
    val tabs = listOf("Invites", "Check ins")
//    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val formattedTime = formatter.format(Date()) // e.g., "07:32 AM"

    val inviteeEntity: List<InviteeEntity>
    var showBottomCard by remember { mutableStateOf(false) }
    var selectedInvitee by remember { mutableStateOf<InviteeEntity?>(null) }



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
                painter = painterResource(id = R.drawable.outdorparty),
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
                            colorResource(id = R.color.teal_200)
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
                        "Invite" to FontAwesomeIcons.Solid.Users,
                        "Edit" to FontAwesomeIcons.Solid.Edit
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
                                    .background(if (isHoveredShare) Color.LightGray.copy(alpha = 0.2f) else Color.Transparent)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onPress = {
                                                isHoveredShare = true
                                                tryAwaitRelease()
                                                isHoveredShare = false
                                            },
                                            onTap = {
                                                when (label) {

                                                    "Share" -> {

                                                        val shareText = """
    You're invited to a special event hosted by ${eventDetail?.hostName}!

    📌 Event Details:
    • Type: ${eventDetail?.eventType}
    • Date: ${eventDetail?.eventDate}
    • Venue: ${eventDetail?.venue}
    • Description: ${eventDetail?.description}
    • Contact: ${eventDetail?.phone}
    • Host: ${eventDetail?.hostName}

    🎟️ Entry Requirement:
    Please note that entry will require a QR code, which is valid for **one-time use only**.  
    If you do not yet have a QR code, kindly download the *Eparty Entry* app and create your identity using your full name and phone number.

    📩 RSVP and join us — all are welcome!
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

                                                    "Invite" -> {
                                                        // ✏️ Navigate or show toast
                                                        showDialog = true
                                                        Toast.makeText(
                                                            context,
                                                            "Invite Friends and family",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }


                                                    "Edit" -> {
                                                        // ✏️ Navigate or show toast
//                                                        showDialog = true
                                                        navController.navigate(Screen.EditEventDetail.createRoute(itemId.toString()))
                                                        Toast.makeText(
                                                            context,
                                                            "Edit event details",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                            }
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = label,
                                        tint = Color.Black,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = label,
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                Column(modifier = Modifier.fillMaxWidth()) {

                    // TabRow with custom colors and pager sync
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        contentColor = Color.Magenta, // default indicator
                        indicator = { tabPositions ->
                            SecondaryIndicator(
                                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                color = colorResource(id = R.color.teal_200) // 🎨 Set your custom color here
                            )
                        },
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))

                    ) {
                        tabs.forEachIndexed { index, title ->
                            val isSelected = pagerState.currentPage == index
                            Tab(
                                selected = isSelected,
                                onClick = {
                                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                                },
                                modifier = Modifier.weight(1f)

                            ) {
                                Text(
                                    text = title,
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = if (isSelected) colorResource(id = R.color.teal_200) else Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }



                    // Pager content area
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) { page ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(16.dp)
                                .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
//                                .background(Color(0xFFF5F5F5))
                        ) {
                            when (page) {
                                0 ->
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
//                                                    Card(
//                                                        modifier = Modifier
//                                                            .fillMaxWidth()
//                                                            .padding(8.dp)
//                                                            .clickable { },
//                                                        shape = RoundedCornerShape(12.dp),
//                                                        colors = CardDefaults.cardColors(containerColor = Color.White)
//                                                    ) {
//                                                        Row(
//                                                            modifier = Modifier
//                                                                .fillMaxWidth()
//                                                                .padding(16.dp),
//                                                            verticalAlignment = Alignment.CenterVertically
//                                                        ) {
//                                                            Column(modifier = Modifier.weight(1f)) {
//                                                                Text(text = invitee[index].fullName)
//                                                                Text(text = invitee[index].phone)
////                                            Text(text = invitee[index].checkInTime)
//                                                            }
//                                                        }
//                                                    }
                                                    Card(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(8.dp)
                                                            .combinedClickable( // This enables both click and longClick
                                                                onClick = { /* Handle normal click if needed */ },
                                                                onLongClick = {
                                                                    selectedInvitee = invitee[index]
                                                                    showBottomCard = true
                                                                    Log.d("LongClick", "Long click detected ${selectedInvitee}")
                                                                }
                                                            ),
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
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                        Spacer(modifier = Modifier.height(32.dp)) // Extra space at the bottom
                                    }

                                1 ->

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

                            }
                        }
                    }
                }


            }


            }

        }

        // Show the Payment Popup if showDialog is true
        if (showDialog) {
            InvitePopup(onDismiss = { showDialog = false }, itemId.toString())

        }




//    if (showBottomCard && selectedInvitee != null) {
//        // Transparent overlay
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Black.copy(alpha = 0.3f))
//                .zIndex(1f)
//                .clickable(
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                ) {
//                    showBottomCard = false
//                }
//        )
//
//        // Fixed bottom card
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
////                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//                .zIndex(2f),
//            shape = RoundedCornerShape(16.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text("Edit", modifier = Modifier
//                    .clickable {
//                        Toast.makeText(context, "Edit ${selectedInvitee?.fullName}", Toast.LENGTH_SHORT).show()
//                        showBottomCard = false
//                    }
//                    .padding(8.dp))
//
//                Text("Delete", modifier = Modifier
//                    .clickable {
//                        Toast.makeText(context, "Delete ${selectedInvitee?.fullName}", Toast.LENGTH_SHORT).show()
//                        showBottomCard = false
//                    }
//                    .padding(8.dp))
//            }
//        }
//
//}

    if (showBottomCard && selectedInvitee != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Transparent overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.3f))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        showBottomCard = false
                    }
                    .zIndex(1f)
            )

            // Bottom card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // 👈 ensure this is at bottom
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                    .then(
                        Modifier.padding(WindowInsets.navigationBars.asPaddingValues())
                    )


                    .zIndex(2f),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Edit Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDialogEdit = true
                                Toast
                                    .makeText(context, "Edit ${selectedInvitee?.fullName}", Toast.LENGTH_SHORT)
                                    .show()
                                showBottomCard = false
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Pen,
                            contentDescription = "Edit",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Edit", fontSize = 16.sp)
                    }

                    // Delete Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                coroutineScope.launch {
                                    selectedInvitee?.inviteId?.let { id ->
                                        inviteeViewModel.deleteInviteeById(id)
                                        Toast
                                            .makeText(context, "Deleted ${selectedInvitee?.fullName}", Toast.LENGTH_SHORT)
                                            .show()
                                        showBottomCard = false
                                    }
                                }
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Trash,
                            contentDescription = "Delete",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Delete", fontSize = 16.sp)
                    }
                }
            }
        }




    }



    if (showDialogEdit) {
//        AddDebtPopUpScreen(onDismiss = { showAddDebtDialog = false })
        selectedInvitee?.inviteId?.let { EditInvitePopup(inviteId = it, onDismiss = { showDialogEdit = false }) }
    }

}



@Preview(showBackground = true)
@Composable
fun EventDetailScreenPreview() {
    EventDetailScreen(navController = rememberNavController(), itemId = "2")
}