package com.st11.epartyentry.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.st11.epartyentry.R
import com.st11.epartyentry.navigation.Screen
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.utils.formatDate
import com.st11.epartyentry.utils.formatDateToReadable
import com.st11.epartyentry.viewmodel.EventsViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.Plus
import org.koin.androidx.compose.koinViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.pink)

    DynamicStatusBar(backgroundColor)  // ✅ Apply dynamic status bar settings

    val eventViewModel: EventsViewModel = koinViewModel()
//    val totalPeople by peopleViewModel.totalPeople.collectAsState()
     val totalEnded by eventViewModel.totalEnded.collectAsState()
    val totalUpcoming by eventViewModel.totalUpcoming.collectAsState()
    val upcomingEvents by eventViewModel.upcomingEvents.collectAsState()
    val currentDate = System.currentTimeMillis()
        val formattedDate = formatDate(currentDate) // Should return "DD-MM-YYYY"


    LaunchedEffect(Unit)  {
       eventViewModel.getAllTotalEnded()
        eventViewModel.getAllTotalUpcoming()
        eventViewModel.getAllUpcomingEvents(formattedDate)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Events", color = Color.White)
                },
                navigationIcon = { /* Optional: Add IconButton here if needed */ },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.pink),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }

    ) { paddingValues ->



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.light_bg_color))
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement =  Arrangement.spacedBy(12.dp)
            ) {
                EventCard(
                    title = "Upcoming Events",
                    count = totalUpcoming ?: 0,
                    imageRes = R.drawable.party01
                )
                EventCard(
                    title = "Ended Events",
                    count = totalEnded ?: 0,
                    imageRes = R.drawable.party02
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "No Data Available",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Gray
//                )
//            }

            Box(
                modifier = Modifier
//                    .fillMaxSize()
                    .fillMaxWidth()
                    .height(300.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
//                                .background(Color(0xFFF5F5F5))
            ) {

                Column {
                    Text(
                        text = "Your upcoming events",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    if(upcomingEvents.isEmpty()){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp), // Add padding around the entire Box
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "You Do not have Upcoming events",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp) // Padding specific to the Text
                            )
                        }

                    }else {

                        upcomingEvents.forEach { event ->

//                    repeat(10) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
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
                                        Text(
                                            text = "On ${formatDateToReadable(event.eventDate)}",
                                            fontWeight = FontWeight.Bold,
                                            color = colorResource(id = R.color.teal_200)
                                        )
                                        Text(
                                            text = "${event.eventType} at ${event.venue}",
                                            color = colorResource(id = R.color.dark)
                                        )
                                    }

                                    Button(
                                        onClick = {
                                            navController.navigate(
                                                Screen.EventDetail.createRoute(
                                                    event.eventId
                                                )
                                            )
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = colorResource(id = R.color.teal_200)
                                        ),
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 6.dp,
                                                vertical = 6.dp
                                            ) // Adjust padding as needed
                                    ) {
                                        Icon(
                                            imageVector = FontAwesomeIcons.Solid.Plus,
                                            contentDescription = "Add",
                                            tint = colorResource(id = R.color.white),
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Add invites", color = Color.White)
                                    }

                                }

                            }
                        } }
                }



            }





        }

    }

}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(navController = rememberNavController())
}

@Composable
fun EventCard(title: String, count: Int, imageRes: Int) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "$title:",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Text(
                text = count.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}
