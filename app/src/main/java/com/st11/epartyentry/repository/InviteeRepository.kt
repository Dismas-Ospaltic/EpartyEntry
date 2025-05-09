package com.st11.epartyentry.repository


import com.st11.epartyentry.data.local.InviteeDao
import com.st11.epartyentry.model.InviteeEntity
import kotlinx.coroutines.flow.Flow


class InviteeRepository(private val inviteeDao: InviteeDao) {

    fun getAllInvitee(eventId: String): Flow<List<InviteeEntity>> = inviteeDao.getAllInvitee(eventId)

    fun getAllTotalInvitee(eventId: String): Flow<Int?> = inviteeDao.getAllTotalInvitee(eventId)

    fun getAllInviteeCheckIn(eventId: String): Flow<List<InviteeEntity>> = inviteeDao.getAllInviteeCheckIn(eventId)

    suspend fun insertInvitee(invitee: InviteeEntity) {
        inviteeDao.insertInvitee(invitee)
    }

    suspend fun updateInvitee(invitee: InviteeEntity) {
        inviteeDao.updateInvitee(invitee)
    }

    suspend fun updateInviteeStatus(isCheckIn: Boolean, eventId: String): Int? {
        return inviteeDao.updateInviteeStatus(isCheckIn, eventId)
    }

//    suspend fun checkInviteeExists(phone: String, eventId: String): Boolean {
//        return inviteeDao.isInviteePresent(phone, eventId)
//    }

    suspend fun checkInviteeExists(phone: String, eventId: String): Boolean {
        return inviteeDao.getInviteeByPhoneAndEvent(phone, eventId) != null
    }



    suspend fun updateCheckIn(phone: String, eventId: String, checkInTime: String, checkInDate: String) {
        inviteeDao.updateCheckIn(phone, eventId, checkInTime, checkInDate)
    }






}