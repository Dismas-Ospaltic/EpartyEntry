package com.st11.epartyentry.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.st11.epartyentry.R
import com.st11.epartyentry.utils.DynamicStatusBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(onCompleteOnboarding: () -> Unit) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()


    val backgroundColor = colorResource(id = R.color.white)
    DynamicStatusBar(backgroundColor)

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            .padding(bottom = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // **Pager for onboarding screens**
        HorizontalPager(
            state = pagerState,
            count = onboardingPages.size,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageContent(onboardingPages[page])
        }

        // **Page indicators**
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = colorResource(id = R.color.pink),
            inactiveColor = Color.Gray
        )

        // **Navigation Buttons**
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (pagerState.currentPage > 0) {
                Button(
                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark))
                ) {
                    Text("Prev")
                }
            }

            if (pagerState.currentPage == onboardingPages.lastIndex) {
                Button(
                    onClick = { onCompleteOnboarding() },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.pink))
                ) {
                    Text("Get Started")
                }
            } else {
                Button(
                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark))
                ) {
                    Text("Next")
                }
            }
        }
    }
}

// **Data class for onboarding pages**
data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

// **Onboarding page content**
@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = "Onboarding Image",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = page.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = page.description, fontSize = 16.sp)
    }
}

// **Onboarding pages list**
val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.people,
        title = "Create a Party Event",
        description = "Create A party Event with friends and family"
    ),
    OnboardingPage(
        imageRes = R.drawable.idcard,
        title = "Your Identity",
        description = "Add your phone number and name to create your Identity"
    ),
    OnboardingPage(
        imageRes = R.drawable.scan,
        title = "Gate Pass",
        description = "Use Your QR code as a gate pass to enter the party and check in"
    )
)
