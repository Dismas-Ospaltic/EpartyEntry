package com.st11.epartyentry.screens

import android.app.Activity
import android.content.Intent
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.st11.epartyentry.R
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.utils.generateQrCodeBitmap
import com.st11.epartyentry.viewmodel.CreateIdentityViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel


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




    val content = userData.userPone.ifBlank { "default-content" }
    val qrBitmap = generateQrCodeBitmap(content, qrSize)

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My QR Code", color = Color.White) },
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
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.light_bg_color))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Hello ${userData.userName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
//            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "use this to enter the event you have been invite.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.teal_200),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                bitmap = qrBitmap.asImageBitmap(),
                contentDescription = "QR Code",
                modifier = Modifier
                    .size(220.dp)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Privacy Policy",
                modifier = Modifier
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://st11-homy.github.io/Eparty-Entry/privacy-policy.html")
                        )
                        context.startActivity(intent)
                    }
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.dark),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                color = colorResource(id = R.color.dark),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QRScreenPreview() {
    QRScreen(navController = rememberNavController())
}