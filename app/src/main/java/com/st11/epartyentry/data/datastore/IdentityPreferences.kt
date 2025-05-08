package com.st11.epartyentry.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ✅ Rename the DataStore extension property
private val Context.userDataStore: DataStore<androidx.datastore.preferences.core.Preferences>
        by preferencesDataStore(name = "user_prefs")

class IdentityPreferences(private val context: Context) {

    companion object {
        val USER_PHONE = stringPreferencesKey("user_phone")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_ID = stringPreferencesKey("user_id")
        val IS_IDENTITY_CREATED = booleanPreferencesKey("is_identity_created")
    }

    // Save user data
    suspend fun saveUserData(userPone: String, userName: String , userId: String) {
        context.userDataStore.edit { preferences ->
            preferences[USER_PHONE] = userPone
            preferences[USER_NAME] = userName
            preferences[USER_ID] = userId
            preferences[IS_IDENTITY_CREATED] = true
        }
    }

    // Retrieve user data as Flow (to observe changes)
    val userData: Flow<UserData> = context.userDataStore.data.map { preferences ->
        UserData(
            userPone = preferences[USER_PHONE] ?: "",
            userName = preferences[USER_NAME] ?: "",
            userId = preferences[USER_ID] ?: "",
            isIdentityCreated = preferences[IS_IDENTITY_CREATED] ?: false
        )
    }

    // Clear user data (logout)
    suspend fun clearUserData() {
        context.userDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Expose isIdentityCreated as Flow<Boolean>
    val isIdentityCreated: Flow<Boolean> = context.userDataStore.data.map { preferences ->
        preferences[IS_IDENTITY_CREATED] ?: false
    }

}

// Data class for user data
data class UserData(
    val userPone: String,
    val userName: String,
    val userId: String,
    val isIdentityCreated: Boolean
)