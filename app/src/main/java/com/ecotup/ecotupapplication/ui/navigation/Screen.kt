package com.ecotup.ecotupapplication.ui.navigation

sealed class Screen(val route : String)
{
    object OptionScreen : Screen("option_screen")
    object LoginUserScreen : Screen("login_user_screen")


}
