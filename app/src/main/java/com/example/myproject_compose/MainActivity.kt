package com.example.myproject_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.myproject_compose.ui.theme.MyProject_composeTheme
import com.example.myproject_compose.ui.theme.mainScreen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProject_composeTheme {
                MaterialTheme {
                    MainScreen()
                }
            }
        }
    }
}
