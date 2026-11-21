package com.atik.grocerystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.atik.grocerystore.core.data.preferences.UserPreferences
import com.atik.grocerystore.ui.theme.GroceryStoreTheme

class MainActivity : ComponentActivity() {
    private val userPreferences: UserPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GroceryStoreTheme {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GroceryStoreTheme {

    }
}