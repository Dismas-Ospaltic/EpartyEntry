package com.st11.epartyentry.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.st11.epartyentry.model.EventEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Update
    suspend fun updateDebt(event: EventEntity)

    @Query("SELECT * FROM events ORDER BY timestamp DESC")
    fun getAllEvents(): Flow<List<EventEntity>>


    @Query("SELECT * FROM events WHERE eventDate > :formattedDate ORDER BY timestamp DESC")
    fun getAllUpcomingEvents(formattedDate: String): Flow<List<EventEntity>>

    @Query("UPDATE events SET eventDate = :eventDate, eventType = :eventType, description = :description, venue = :venue, phone = :phone, hostName = :hostName, eventId = :eventId WHERE eventId = :eventId")
    suspend fun editEventDetail(eventDate: String, eventType: String, description: String, venue: String, phone: String, hostName: String, eventId: String): Int?


    @Query("SELECT COUNT(*) FROM events WHERE eventDate > :formattedDate")
    fun getAllTotalUpcoming(formattedDate: String): Flow<Int?>

    @Query("SELECT COUNT(*) FROM events WHERE eventDate < :formattedDate")
    fun getAllTotalEnded(formattedDate: String): Flow<Int?>

    @Query("SELECT * FROM events WHERE eventId = :eventId")
    suspend fun getEventById(eventId: String): EventEntity?


}