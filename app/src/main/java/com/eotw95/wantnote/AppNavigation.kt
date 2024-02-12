package com.eotw95.wantnote

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eotw95.wantnote.screen.AddWant
import com.eotw95.wantnote.screen.EditWant
import com.eotw95.wantnote.screen.WantList

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        builder = {
            composable(Screens.Home.route) {
                WantList()
            }
            composable(Screens.Add.route) {
                AddWant(
                    onClickAdd = { navController.navigateUp() }
                )
            }
            composable(Screens.Edit.route) {
                EditWant()
            }
        }
    )
}

sealed class Screens(
    val route: String
) {
    object Home: Screens("home")
    object Add: Screens("add")
    object Edit: Screens("edit")
}