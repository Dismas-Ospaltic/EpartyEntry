package com.st11.epartyentry.screens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.st11.epartyentry.R
import com.st11.epartyentry.navigation.Screen
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.utils.ShimmerBox
import com.st11.epartyentry.utils.generateQrCodeBitmap
import com.st11.epartyentry.viewmodel.CreateIdentityViewModel
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bell
import compose.icons.fontawesomeicons.solid.Lock
import compose.icons.fontawesomeicons.solid.Trash
import compose.icons.fontawesomeicons.solid.Users
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.pink)
    val createIdentityViewModel: CreateIdentityViewModel = getViewModel()
    val userData by createIdentityViewModel.userData.collectAsState()

//    val qrBitmap = remember(userData.userPone) {
//        generateQrCodeBitmap(userData.userPone)

    val paddingDp: Dp = 4.dp
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val paddingPx = with(density) { paddingDp.toPx() }
    val qrSize = (screenWidthPx - paddingPx * 2).toInt().coerceAtLeast(100) // Minimum size fallback

//    val content = userData.userPone.ifBlank { "default-content" }
//    val content = userData.userPone.takeIf { it.isNotBlank() } ?: "default-content"
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current

    var showImage by remember { mutableStateOf(false) }

    // Delay effect

    LaunchedEffect(userData.userPone) {
        val content = userData.userPone.takeIf { it.isNotBlank() } ?: "default-content"
        qrBitmap = generateQrCodeBitmap(content, qrSize)
        delay(3000)
        showImage = true
    }
//    LaunchedEffect(Unit) {
//        qrBitmap = generateQrCodeBitmap(content, qrSize)
//        delay(3000) // 3 seconds
//        showImage = true
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Identity & Settings", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.light_bg_color))

        ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(paddingValues)
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
                    bottom = paddingValues.calculateBottomPadding() + 100.dp
                )
                .verticalScroll(rememberScrollState()),
//                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outdoorparty02),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                        .clip(RoundedCornerShape(12.dp))
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
                                startY = 0f
                            )
                        )
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-50).dp), // Moves the whole Box down (overlapping the previous Box)
                contentAlignment = Alignment.Center // This centers the Column inside this Box
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user01),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Text(
                        text = "Hello ${userData.userName}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                    .offset(y = (-20).dp)
//                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Use this QR code to enter the events you have been invited.",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.dark),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )



                    if (showImage) {
                        qrBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "QR Code",
                                modifier = Modifier
                                    .size(250.dp)
                                    .padding(12.dp)
                            )
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            repeat(1) {
                                ShimmerBox(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(200.dp)
                                        .height(200.dp)
                                )
                            }

                        }
                    }


                }
            }


            Spacer(modifier = Modifier.height(16.dp))



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                SettingCard(
                    icon = compose.icons.FontAwesomeIcons.Solid.Lock,
                    title = "Privacy Policy",
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://st11-homy.github.io/Eparty-Entry/privacy-policy.html")
                        )
                        context.startActivity(intent)
                    }
                )

//
//                var notificationsEnabled by remember { mutableStateOf(true) }
//
//                SettingCard(
//                    icon = compose.icons.FontAwesomeIcons.Solid.Bell,
//                    title = "Notifications",
//                    trailing = {
//                        Switch(
//                            colors = SwitchDefaults.colors(
//                                checkedThumbColor = colorResource(id = R.color.dark),
//                                checkedTrackColor = colorResource(id = R.color.dark).copy(alpha = 0.5f),
//                                uncheckedThumbColor = Color.LightGray,
//                                uncheckedTrackColor = Color.Gray
//                            ),
//                            checked = notificationsEnabled,
//                            onCheckedChange = { notificationsEnabled = it }
//                        )
//                    },
//                    onClick = { notificationsEnabled = !notificationsEnabled }
//                )

                SettingCard(
                    icon = compose.icons.FontAwesomeIcons.Solid.Trash,
                    title = "Delete Identity",
                    onClick = {
                        createIdentityViewModel.clearUserIdentity()
                        navController.navigate(
                            Screen.Splash.route
                        )
                    }
                )
            }

        }
    }
    }
}


@Preview(showBackground = true)
@Composable
fun QRScreenPreview() {
    QRScreen(navController = rememberNavController())
}



@Composable
fun SettingCard(
    icon: ImageVector,
    title: String,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorResource(id = R.color.dark),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )

            if (trailing != null) {
                trailing()
            }
        }
    }
}
