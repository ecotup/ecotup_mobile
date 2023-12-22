package com.ecotup.ecotupapplication.ui.navigation

sealed class Screen(val route: String) {
    object UserScreen : Screen("user_screen")
    object DriverScreen : Screen("driver_screen")
    object OptionScreen : Screen("option_screen")
    object AuthScreen : Screen("auth_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterUserScreen : Screen("register_user_screen")
    object RegisterUserScreenPassword :
        Screen("register_user_screen_password/{name}/{email}/{phone}/{lat}/{long}")

    object RegisterDriverScreen : Screen("register_driver_screen")
    object RegisterDriverScreenPassword :
        Screen("register_driver_screen_password/{name}/{email}/{phone}/{lat}/{long}/{type}/{license}")

    object RegisterDriverScreenVehicle :
        Screen("register_driver_screen_vehicle/{name}/{email}/{phone}/{lat}/{long}")

    object HomeScreenUser : Screen("home_screen_user")
    object SubscriptionScreenUser : Screen("subscription_screen_user")
    object ScanningScreenUser : Screen("scanning_screen_user")
    object HistoryScreenUser : Screen("history_screen_user")
    object SettingScreenUser : Screen("setting_screen_user")
    object HomeScreenDriver : Screen("home_screen_driver")
    object IncomeScreenDriver : Screen("income_screen_driver")
    object HistoryScreenDriver : Screen("history_screen_driver")
    object SettingScreenDriver : Screen("setting_screen_driver")
    object OnboardingScreen : Screen("onboarding_screen")
    object EditProfileScreenUser : Screen("edit_profile_screen_user")
    object EditProfileScreenDriver : Screen("edit_profile_screen_driver")
    object EditAddressScreenUser : Screen("edit_address_screen_user")
    object EditAddressScreenDriver : Screen("edit_address_screen_driver")
    object AboutScreen : Screen("about_screen")
    object DetailHistoryTransactionScreenUser : Screen("detail_history_transaction_screen_user")
    object DetailHistoryTransactionScreenDriver : Screen("detail_history_transaction_screen_driver")


    // Home Navigation User
    object DetailSubscriptionScreen :
        Screen("detail_subscription_screen/{image}/{pickup}/{title}/{price}/{description}")

    // Scanning User
    object ResultScanningScreenUser : Screen("result_scanning_screen_user/{resultImage}")

    // Reward User
    object RewardScreen : Screen("reward_screen")
    object DetailRewardScreen : Screen("detail_reward_screen/{iR}/{nR}/{pR}/{dR}")

    object SuccessGetRewardScreen : Screen("success_get_reward_screen")

    // Error page
    object SubscriptionMonthlyErrorScreen :
        Screen("subscription_monthly_error_screen/{subscription}")

    object SubscriptionWeeklyErrorScreen : Screen("subscription_weekly_error_screen/{subscription}")
}

