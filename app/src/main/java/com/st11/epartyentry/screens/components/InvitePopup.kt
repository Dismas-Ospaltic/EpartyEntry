package com.st11.epartyentry.screens.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.st11.epartyentry.R
import com.st11.epartyentry.model.InviteeEntity
import com.st11.epartyentry.model.countries
import com.st11.epartyentry.screens.generateUserId
import com.st11.epartyentry.viewmodel.InviteeViewModel
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitePopup(onDismiss: () -> Unit, itemId: String) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }
    var countryCode by remember { mutableStateOf("+254") }  // Default to Kenya code
    var selectedCountry by remember { mutableStateOf("Kenya") }
    var expanded by remember { mutableStateOf(false) }  // State to control dropdown expansion
    val backgroundColor = colorResource(id = R.color.pink)
    val context = LocalContext.current


    val inviteeViewModel: InviteeViewModel = koinViewModel()


    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add Invitee") },
        text = {
            Column {

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

            }
        },
        confirmButton = {

            TextButton(onClick = { /* Handle submission */
                if (isValid && fullName.isNotBlank()) {
                    val fullPhoneNumber = "$countryCode$phoneNumber"
//                    inviteeViewModel.insertInvitee(invitee = InviteeEntity())
                    CoroutineScope(Dispatchers.Main).launch {
                        inviteeViewModel.insertInvitee(invitee = InviteeEntity(
                            fullName = fullName,
                            phone = fullPhoneNumber,
                            eventId = itemId,
                            inviteId = generateSixDigitRandomNumber().toString()
                        ))
                    }
                    onDismiss()
                }else{
                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Add", color = colorResource(R.color.green))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel", color = colorResource(R.color.red))
            }
        }
    )
}

fun generateSixDigitRandomNumber(): Int {
    return Random.nextInt(100000, 1000000)  // Generates a random number between 100000 (inclusive) and 1000000 (exclusive)
}