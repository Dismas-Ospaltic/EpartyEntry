package com.st11.epartyentry.screens



import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.st11.epartyentry.R
import com.st11.epartyentry.model.countries
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.viewmodel.CreateIdentityViewModel
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateIdentityScreen(navController: NavController) {
    val context = LocalContext.current
    val backgroundColor = colorResource(id = R.color.pink)
    DynamicStatusBar(backgroundColor)

    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }
    var countryCode by remember { mutableStateOf("+254") }  // Default to Kenya code
    var selectedCountry by remember { mutableStateOf("Kenya") }
    var expanded by remember { mutableStateOf(false) }  // State to control dropdown expansion
    val createIdentityViewModel: CreateIdentityViewModel = koinViewModel()

    val imageLoader = remember { // Use remember for the imageLoader
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val imageModel = ImageRequest.Builder(context)
                .data("android.resource://${context.packageName}/raw/peopleglow")
                .crossfade(true)
                .error(android.R.drawable.ic_menu_gallery) // Placeholder on error
                .build()

            AsyncImage(
                model = imageModel,
                imageLoader = imageLoader,
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
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Create Your Identity",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Country Picker Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded } // Toggle on click
                ) {
                    OutlinedTextField(
                        value = selectedCountry,
                        onValueChange = {}, // Value is changed by DropdownMenuItem click
                        label = { Text("Country") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(), // Crucial for positioning the dropdown
                        readOnly = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
                            focusedBorderColor = backgroundColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        countries.forEach { country ->
                            DropdownMenuItem(
                                text = { Text("${country.name} (${country.code})") },
                                onClick = {
                                    countryCode = country.code
                                    selectedCountry = country.name
                                    expanded = false
                                    // Re-validate phone number if country code changes
                                    val full = "${country.code}${phoneNumber}"
                                    isValid = if (phoneNumber.isNotEmpty()) { // only validate if phone number part is not empty
                                        try {
                                            val phoneUtil = PhoneNumberUtil.createInstance(context)
                                            val parsed = phoneUtil.parse(full, null)
                                            phoneUtil.isValidNumber(parsed)
                                        } catch (e: Exception) {
//                                            Log.e("PhoneValidation", "Error parsing $full", e)
                                            false
                                        }
                                    } else {
                                        false // or true if an empty number is considered valid until typed
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it.filter { char -> char.isDigit() } // Allow only digits

                        val full = "$countryCode$phoneNumber"
                        isValid = if (phoneNumber.isNotEmpty()) { // only validate if phone number part is not empty
                            try {
                                val phoneUtil = PhoneNumberUtil.createInstance(context)
                                val parsed = phoneUtil.parse(full, null) // Use country code with number
                                phoneUtil.isValidNumber(parsed)
                            } catch (e: Exception) {
//                                Log.e("PhoneValidation", "Error parsing $full", e)
                                false
                            }
                        } else {
                            false // Or handle as per your logic for empty phone number
                        }

//                        if (isValid) {
////                            Log.d("PhoneInput", "Valid number: $full")
//                        } else if (phoneNumber.isNotEmpty()) {
////                            Log.d("PhoneInput", "Invalid number attempt: $full")
//                        }
                    },
                    label = { Text("Phone Number") },
                    leadingIcon = {
                        Text(
                            text = countryCode,
                            modifier = Modifier.padding(end = 4.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant // Or specific color
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )


                if (!isValid && phoneNumber.isNotEmpty()) {
                    Text(
                        text = "Invalid phone number for selected country",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (isValid && fullName.isNotBlank()) {
                            val fullPhoneNumber = "$countryCode$phoneNumber"
//                            Log.d("CreateIdentity", "Proceeding with: Name: $fullName, Phone: $fullPhoneNumber")
                            // Proceed with valid phone and full name
                            createIdentityViewModel.saveUserIdentity(fullPhoneNumber, fullName, generateUserId())
                            navController.navigate("home")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = fullName.isNotBlank() && isValid && phoneNumber.isNotEmpty()
                ) {
                    Text("Continue", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateIdentityScreenPreview() {
    CreateIdentityScreen(navController = rememberNavController())
}


fun generateUserId(): String {
    return java.util.UUID.randomUUID().toString()
}