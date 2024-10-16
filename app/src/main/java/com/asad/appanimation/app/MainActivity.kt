package com.asad.appanimation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.asad.appanimation.app.navigation.AppAnimationNavHost
import com.asad.appanimation.theme.AppAnimationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAnimationTheme {
                AppAnimationNavHost()
            }
        }
    }
}
