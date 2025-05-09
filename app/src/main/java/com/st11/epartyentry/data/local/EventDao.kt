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


    @Query("SELECT COUNT(*) FROM events WHERE eventDate > :formattedDate")
    fun getAllTotalUpcoming(formattedDate: String): Flow<Int?>

    @Query("SELECT COUNT(*) FROM events WHERE eventDate < :formattedDate")
    fun getAllTotalEnded(formattedDate: String): Flow<Int?>

    @Query("SELECT * FROM events WHERE eventId = :eventId")
    suspend fun getEventById(eventId: String): EventEntity?


}