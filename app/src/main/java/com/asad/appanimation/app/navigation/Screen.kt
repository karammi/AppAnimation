package com.asad.appanimation.app.navigation

sealed class Screen(open val route: String) {

        object HomeScreen: Screen(route = "HomeScreen")
}