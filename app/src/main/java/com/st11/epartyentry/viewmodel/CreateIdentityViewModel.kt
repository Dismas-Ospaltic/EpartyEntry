package com.st11.epartyentry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st11.epartyentry.data.datastore.IdentityPreferences
import com.st11.epartyentry.data.datastore.UserData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreateIdentityViewModel(
    private val identityPreferences: IdentityPreferences
) : ViewModel() {

    // Observe isIdentityCreated separately
    val isIdentityCreated: StateFlow<Boolean> = identityPreferences.isIdentityCreated
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)


    // StateFlow to observe user data
    val userData: StateFlow<UserData> = identityPreferences.userData
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserData("", "", "", false))


    // Save user identity
    fun saveUserIdentity(phone: String, name: String, userId: String) {
        viewModelScope.launch {
            identityPreferences.saveUserData(phone, name , userId)

        }
    }

    // Clear identity (e.g., on logout)
    fun clearUserIdentity() {
        viewModelScope.launch {
            identityPreferences.clearUserData()
        }
    }
}
