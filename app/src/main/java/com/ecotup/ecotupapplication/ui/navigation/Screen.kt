package com.ecotup.ecotupapplication.ui.navigation

sealed class Screen(val route : String)
{
    object UserScreen : Screen("user_screen")
    object DriverScreen : Screen("driver_screen")
    object OptionScreen : Screen("option_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterUserScreen : Screen("register_user_screen")
    object RegisterUserScreenPassword : Screen("register_user_screen_password")
    object RegisterDriverScreen : Screen("register_driver_screen")
    object RegisterDriverScreenPassword : Screen("register_driver_screen_password")
    object RegisterDriverScreenVehicle : Screen("register_driver_screen_vehicle")
    object HomeScreenUser : Screen("home_screen_user")
    object SubscriptionScreenUser : Screen("subscription_screen_user")
    object ScanningScreenUser : Screen("scanning_screen_user")
    object HistoryScreenUser : Screen("history_screen_user")
    object SettingScreenUser : Screen("setting_screen_user")
    object HomeScreenDriver : Screen("home_screen_driver")
    object HistoryScreenDriver : Screen("history_screen_driver")
    object SettingScreenDriver : Screen("setting_screen_driver")
    object OnboardingScreen : Screen("onboarding_screen")
}
