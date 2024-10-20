package com.asad.appanimation.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asad.appanimation.home.presentation.screen.HomeScreen
import com.asad.appanimation.showcase.presentation.screen.AnimationShowcaseScreen
import java.io.File

@Composable
fun AppAnimationNavHost() {

    val navController = rememberNavController()

    val onFolderClicked: (File) -> Unit = { file ->
        navController.navigate(Screen.AnimationShowcaseScreen.onCreateRoute(file.path))
    }

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(onFolderClick = onFolderClicked)
        }

        composable(
            route = Screen.AnimationShowcaseScreen.route,
            arguments = listOf(
                navArgument(
                    name = NavConstants.ANIMATION_PATH_ARGUMENT,
                    builder = {
                        type = NavType.StringType
                        nullable = false
                        defaultValue = ""
                    }
                )
            )
        ) { navBackStackEntry ->
            val animationPathArgs = navBackStackEntry.arguments?.getString(NavConstants.ANIMATION_PATH_ARGUMENT)
            animationPathArgs?.let { path -> AnimationShowcaseScreen(path = path) }
        }
    }

}