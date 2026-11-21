package com.atik.grocerystore.feature.auth.di

import com.atik.grocerystore.feature.auth.data.preferences.UserPreferences
import com.atik.grocerystore.feature.auth.data.preferences.UserPreferencesImpl
import com.atik.grocerystore.feature.auth.data.preferences.userPreferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val authPreferencesModule = module {
    single { androidContext().userPreferencesDataStore }

    single<UserPreferences> { UserPreferencesImpl(dataStore = get()) }
}