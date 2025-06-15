package com.st11.epartyentry.repository


import com.st11.epartyentry.data.local.EventDao
import com.st11.epartyentry.model.EventEntity
import com.st11.epartyentry.utils.formatDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepository(private val eventDao: EventDao) {


    fun getAllEvents(): Flow<List<EventEntity>> = eventDao.getAllEvents()

    fun getAllUpcomingEvents(formattedDate: String): Flow<List<EventEntity>> = eventDao.getAllUpcomingEvents(formattedDate)
//

//    fun getAllTotalUpcoming(formattedDate: String): Flow<Int?> = eventDao.getAllTotalUpcoming(formattedDate)
//    fun getAllTotalEnded(formattedDate: String): Flow<Int?> = eventDao.getAllTotalEnded(formattedDate)
    suspend fun insertEvent(event: EventEntity) {
        eventDao.insertEvent(event)
    }
    suspend fun updateEvent(event: EventEntity) {
        eventDao.updateDebt(event)
    }
    suspend fun getEventById(eventId: String): EventEntity? {
        return eventDao.getEventById(eventId)
    }



    fun getAllTotalUpcoming(): Flow<Int> {
        val currentDate = System.currentTimeMillis()
        val formattedDate = formatDate(currentDate) // Should return "DD-MM-YYYY"
        return eventDao.getAllTotalUpcoming(formattedDate)
            .map { total -> total ?: 0 }  // Convert NULL to 0.0
    }

    fun getAllTotalEnded(): Flow<Int> {
        val currentDate = System.currentTimeMillis()
        val formattedDate = formatDate(currentDate) // Should return "DD-MM-YYYY"
        return eventDao.getAllTotalEnded(formattedDate)
            .map { total -> total ?: 0 }  // Convert NULL to 0.0
    }

    suspend fun  editEventDetail(eventDate: String, eventType: String, description: String, venue: String, phone: String, hostName: String, eventId: String): Boolean{
        val rowsUpdated = eventDao.editEventDetail(eventDate, eventType, description, venue, phone, hostName, eventId) ?: 0
        return rowsUpdated > 0
    }



}

