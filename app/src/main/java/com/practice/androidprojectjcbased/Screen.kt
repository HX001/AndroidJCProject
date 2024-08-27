package com.practice.androidprojectjcbased

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object InputScreen : Screen("input_screen")
    object ContentListScreen : Screen("contentList_screen")
    object FilterScreen : Screen("filter_screen")
}