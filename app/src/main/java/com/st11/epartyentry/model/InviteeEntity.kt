package com.st11.epartyentry.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invitee")
data class InviteeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val checkInDate: String = null.toString(), //DD-MM-YYYY
    val fullName: String,
    val phone: String,
    val checkInTime: String = null.toString(),
    val eventId: String,
    val isCheckIn: Int = 0,
    val inviteId: String,
    val timestamp: Long = System.currentTimeMillis()
)
