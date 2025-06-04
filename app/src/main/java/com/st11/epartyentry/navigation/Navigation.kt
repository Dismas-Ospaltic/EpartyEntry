package com.st11.epartyentry.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.st11.epartyentry.screens.CreateEventScreen
import com.st11.epartyentry.screens.CreateIdentityScreen
import com.st11.epartyentry.screens.EditEventScreen
import com.st11.epartyentry.screens.HistoryScreen
import com.st11.epartyentry.screens.OnboardingScreen
import com.st11.epartyentry.screens.QRScreen
import com.st11.epartyentry.screens.SplashScreen
import com.st11.epartyentry.screens.EventDetailScreen
import com.st11.epartyentry.screens.HomeScreen
import com.st11.epartyentry.viewmodel.CreateIdentityViewModel
import com.st11.epartyentry.viewmodel.OnboardingViewModel
import org.koin.androidx.compose.getViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object QR : Screen("My QR")
    object History : Screen("Events overview")

    object EventDetail : Screen("eventDetail/{itemId}") {
        fun createRoute(itemId: String) = "eventDetail/$itemId"
    }
    object CreateEvent : Screen("createEvent")

    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object CreateIdentity : Screen("createIdentity")

    object EditEventDetail : Screen("editEventDetail/{itemId}") {
        fun createRoute(itemId: String) = "editEventDetail/$itemId"
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {

    val mainViewModel: OnboardingViewModel = getViewModel()
    val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState(initial = false)

    val createIdentityViewModel: CreateIdentityViewModel = getViewModel()
    val isIdentityCreated by createIdentityViewModel.isIdentityCreated.collectAsState()


    AnimatedNavHost(
        navController,
        startDestination = Screen.Splash.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.QR.route) { QRScreen(navController) }
        composable(Screen.History.route) { HistoryScreen(navController) }
        composable(Screen.CreateEvent.route) { CreateEventScreen(navController) }
        composable(Screen.EventDetail.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            EventDetailScreen(navController, itemId)
        }
        composable(Screen.EditEventDetail.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            EditEventScreen(navController, itemId)
        }

        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigate = {
                    when {
                        isOnboardingCompleted -> navController.navigate(Screen.CreateIdentity.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }

                        else -> navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }

                })
        }

        composable(Screen.CreateIdentity.route) {
            LaunchedEffect(isIdentityCreated) {
                if (isIdentityCreated) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.CreateIdentity.route) { inclusive = true }
                    }
                }
            }

            // Always show CreateIdentityScreen unless already created
            CreateIdentityScreen(navController)
        }



        composable(Screen.Onboarding.route) {  OnboardingScreen( onCompleteOnboarding = {
            mainViewModel.completeOnboarding()
            navController.navigate(Screen.CreateIdentity.route) {
                popUpTo(Screen.Onboarding.route) { inclusive = true }

            }
        }
        )
        }

    }
}