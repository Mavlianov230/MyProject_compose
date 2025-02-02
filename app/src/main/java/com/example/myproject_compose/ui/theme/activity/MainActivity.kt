package com.example.myproject_compose.ui.theme.activity

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.myproject_compose.ui.theme.Theme.MyProject_composeTheme

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
