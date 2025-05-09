package com.st11.epartyentry.di


import com.st11.epartyentry.data.datastore.IdentityPreferences
import com.st11.epartyentry.data.datastore.OnboardingPreferencesManager
import com.st11.epartyentry.data.local.AppDatabase
import com.st11.epartyentry.repository.EventRepository
import com.st11.epartyentry.repository.InviteeRepository
import com.st11.epartyentry.viewmodel.CreateIdentityViewModel
import com.st11.epartyentry.viewmodel.EventsViewModel
import com.st11.epartyentry.viewmodel.InviteeViewModel
import com.st11.epartyentry.viewmodel.OnboardingViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
    // Define ViewModel injection
    viewModel { OnboardingViewModel(get()) }

    // Define PreferencesManager injection
    single { OnboardingPreferencesManager(get()) }

    single { IdentityPreferences(get()) }

    viewModel { CreateIdentityViewModel(get()) }

    single { AppDatabase.getDatabase(get()).eventDao() }
    single { EventRepository(get()) }
    viewModel {  EventsViewModel(get()) }

    single { AppDatabase.getDatabase(get()).inviteeDao() }
    single { InviteeRepository(get()) }
    viewModel {  InviteeViewModel(get()) }






}