package com.st11.epartyentry.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.st11.epartyentry.R
import com.st11.epartyentry.navigation.Screen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Comments
import compose.icons.fontawesomeicons.solid.Plus
import org.koin.androidx.compose.koinViewModel
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.UUID


@Composable
fun HomeFAB(navController: NavController) {
    val primaryColor = colorResource(id = R.color.pink)
    val whiteColor = colorResource(id = R.color.white)

    FloatingActionButton(
        onClick = { navController.navigate(Screen.CreateEvent.route) },
        containerColor = primaryColor,
        modifier = Modifier
            .padding(bottom = 32.dp, end = 16.dp)
            .zIndex(1f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Plus,
                contentDescription = "Add icon",
                tint = whiteColor,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Create Event",
                color = whiteColor,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeFABPreview() {
    HomeFAB(navController = rememberNavController())
}
