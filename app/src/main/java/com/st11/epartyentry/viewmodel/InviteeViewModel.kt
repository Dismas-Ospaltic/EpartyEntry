package com.st11.epartyentry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st11.epartyentry.model.InviteeEntity
import com.st11.epartyentry.repository.InviteeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class InviteeViewModel(private val inviteeRepository: InviteeRepository) : ViewModel() {

   fun getAllInvitee(eventId: String) = inviteeRepository.getAllInvitee(eventId)

   fun getAllTotalInvitee(eventId: String) = inviteeRepository.getAllTotalInvitee(eventId)

   fun getAllInviteeCheckIn(eventId: String) = inviteeRepository.getAllInviteeCheckIn(eventId)

   suspend fun insertInvitee(invitee: InviteeEntity) = inviteeRepository.insertInvitee(invitee)

   suspend fun updateInvitee(invitee: InviteeEntity) = inviteeRepository.updateInvitee(invitee)

   suspend fun updateInviteeStatus(isCheckIn: Boolean, eventId: String) = inviteeRepository.updateInviteeStatus(isCheckIn, eventId)

   fun updateCheckIn(phone: String, eventId: String, checkInTime: String, checkInDate: String) {
      viewModelScope.launch {
         inviteeRepository.updateCheckIn(phone, eventId, checkInTime, checkInDate)
      }
   }




//   private val _inviteeExists = MutableStateFlow<Boolean?>(null)
//   val inviteeExists: StateFlow<Boolean?> = _inviteeExists
//
//   fun checkInvitee(phone: String, eventId: String) {
//      viewModelScope.launch {
//         val exists = inviteeRepository.checkInviteeExists(phone, eventId)
//         _inviteeExists.value = exists
//      }
//   }


   suspend fun checkInvitee(phone: String, eventId: String): Boolean {
      return inviteeRepository.checkInviteeExists(phone, eventId)
   }

//suspend fun checkInvitee(phone: String, eventId: String): Boolean {
//   return inviteeRepository.checkInviteeExists(phone, eventId)
//}


}