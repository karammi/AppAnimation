package com.asad.appanimation.app.navigation


sealed class Screen(open val route: String) {

    open fun onCreateRoute(vararg args: String): String {
        return route
    }

    data object HomeScreen : Screen(route = "HomeScreen")

    data object AnimationShowcaseScreen :
        Screen(route = "AnimationShowcaseScreen?animationPath={animationPath}") {
        override fun onCreateRoute(vararg args: String): String {
            var route = "AnimationShowcaseScreen"
            if (args.isNotEmpty()) {
                route += "?animationPath=${args[0]}"
            }
            return route
        }
    }
}

object NavConstants {
    const val HOME_ROUTE = "HomeScreen"
    const val ANIMATION_SHOWCASE_SCREEN_ROUTE = "AnimationShowcaseScreen"
    const val ANIMATION_PATH_ARGUMENT = "animationPath"
}