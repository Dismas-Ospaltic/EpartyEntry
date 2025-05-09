package com.st11.epartyentry.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val eventDate: String, //DD-MM-YYYY
    val eventType: String,
    val description: String,
    val venue: String,
    val phone: String,
    val hostName: String,
    val eventId: String,
    val timestamp: Long = System.currentTimeMillis()
)
