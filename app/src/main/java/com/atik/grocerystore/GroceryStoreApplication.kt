package com.atik.grocerystore

import android.app.Application
import com.atik.grocerystore.core.di.appModule
import com.atik.grocerystore.feature.auth.di.authModule
import com.atik.grocerystore.feature.auth.di.authPreferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GroceryStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GroceryStoreApplication)
            modules(
                authPreferencesModule,
                authModule,
                appModule
            )
        }
    }
}