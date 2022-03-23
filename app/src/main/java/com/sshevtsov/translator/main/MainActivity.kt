package com.sshevtsov.translator.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sshevtsov.translator.ui.navigation.ScreenNavHost
import com.sshevtsov.translator.ui.theme.TranslatorTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TranslatorTheme {
        val navController = rememberNavController()
        ScreenNavHost(navController = navController)
      }
    }
  }
}