package com.st11.epartyentry.screens

import android.app.Activity
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
import com.st11.epartyentry.model.EventEntity
import com.st11.epartyentry.utils.DynamicStatusBar
import com.st11.epartyentry.viewmodel.EventsViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.pink)
    val errorColor = colorResource(id = R.color.red)

    DynamicStatusBar(backgroundColor)  // ✅ Apply dynamic status bar settings


    val interactionSource = remember { MutableInteractionSource() }
    var isHovered by remember { mutableStateOf(false) }

    val buttonColor = colorResource(id = R.color.teal_700) // Button color
    val textColor = colorResource(id = R.color.white) // Text color

    val eventViewModel: EventsViewModel = koinViewModel()


    // State variables for input fields
    var partyType by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var venue by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var host by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    // Validation errors
    var partyTypeError by remember { mutableStateOf(false) }
    var dateError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var venueError by remember { mutableStateOf(false) }
    var contactError by remember { mutableStateOf(false) }
    var hostError by remember { mutableStateOf(false) }


    fun validateInputs(): Boolean {
        partyTypeError = partyType.isBlank()
        dateError = date.isBlank()
        descriptionError = description.isBlank()
        venueError = venue.isBlank()
        contactError = contact.isBlank()
        hostError = host.isBlank()

        return !(partyTypeError || dateError || descriptionError || venueError || contactError || hostError)
    }


    // Celebration types
    val partyTypes = listOf(
        "Other", "Birthday", "Wedding", "Homecoming", "Graduation", "Anniversary",
        "Baby Shower", "Engagement Party", "Housewarming", "Retirement Party",
        "Farewell Party", "Welcome Party", "Bridal Shower", "Reunion",
        "New Year’s Eve Party", "Christmas Party", "Eid Celebration",
        "Thanksgiving Dinner", "Baptism", "Naming Ceremony", "First Communion"
    )

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    fun showDatePicker() {
        android.app.DatePickerDialog(
            context,
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                date = dateFormat.format(selectedDate.time) // Format date to "YYYY-MM-DD"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Create An Event", color = Color.White) }, // - Item $itemId
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(if (isHovered) Color.Gray.copy(alpha = 0.3f) else Color.Transparent)
                            .hoverable(interactionSource = interactionSource, enabled = true)
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
                    containerColor = colorResource(id = R.color.pink),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()) // ✅ Enable scrolling
        ) {



                Column(
                    modifier = Modifier
                        .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)

                ) {

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        TextField(
                            value = partyType,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Event Type") },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            isError = partyTypeError,
                            singleLine = true,
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            partyTypes.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        partyType = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    if (partyTypeError) {
                        Text("Event type is required", color = errorColor, fontSize = 12.sp)
                    }

                    // Date Picker TextField
                    TextField(
                        value = date,
                        onValueChange = {},
                        label = { Text("Event Date") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        readOnly = true,
                        isError = dateError,
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker() }) {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.Calendar,
                                    contentDescription = "calendar icon",
                                    tint = colorResource(id = R.color.pink),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                    if (dateError) {
                        Text("Event date is required", color = errorColor, fontSize = 12.sp)
                    }

                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 200.dp) // Adjust height for ~4 lines
                            .verticalScroll(rememberScrollState()),
                        isError = descriptionError,
                        textStyle = LocalTextStyle.current.copy(lineHeight = 20.sp),
                        maxLines = 4,
                        singleLine = false
                    )
                    if (descriptionError) {
                        Text("Enter party description", color = errorColor, fontSize = 12.sp)
                    }

                    TextField(
                        value = venue,
                        onValueChange = { venue = it },
                        label = { Text("venue e.g Lyla's Place") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = venueError,
                        singleLine = true
                    )
                    if (venueError) {
                        Text("Event Venue is required", color = errorColor, fontSize = 12.sp)
                    }

                    TextField(
                        value = contact,
                        onValueChange = { contact = it },
                        label = { Text("Phone") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = contactError,
                        singleLine = true
                    )
                    if (contactError) {
                        Text("Phone number is required", color = errorColor, fontSize = 12.sp)
                    }

                    TextField(
                        value = host,
                        onValueChange = { host = it },
                        label = { Text("Host Name") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = hostError,
                        singleLine = true
                    )
                    if (hostError) {
                        Text("Host Name is required", color = errorColor, fontSize = 12.sp)
                    }


                    Button(
                        onClick = {  if (validateInputs()) {
                            // Handle saving the data here


                                eventViewModel.insertEvent(
                                    EventEntity(
                                        eventType = partyType,
                                        eventDate = date,
                                        description = description,
                                        venue = venue,
                                        phone = contact,
                                        eventId = generateEightDigitRandomNumber().toString(),
                                        hostName = host
                                    )
                                )


                            navController.popBackStack()
                        }},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.pink)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Create", color = Color.White)
                    }


                }



        }
    }

}

@Preview(showBackground = true)
@Composable
fun CreateEventScreenPreview() {
    CreateEventScreen(navController = rememberNavController())
}


fun generateEightDigitRandomNumber(): Int {
    return Random.nextInt(10_000_000, 100_000_000)
}