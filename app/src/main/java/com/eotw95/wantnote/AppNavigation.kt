package com.eotw95.wantnote

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eotw95.wantnote.screen.AddWant
import com.eotw95.wantnote.screen.EditWant
import com.eotw95.wantnote.screen.PreviewWant
import com.eotw95.wantnote.screen.WantList

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        builder = {
            composable(Screens.Home.route) {
                WantList(
                    onClickItem = { navController.navigate(Screens.Preview.route) }
                )
            }
            composable(Screens.Add.route) {
                AddWant(
                    onClickAdd = { navController.navigate(Screens.Home.route) }
                )
            }
            composable(Screens.Preview.route) {
                PreviewWant(
                    onClickBack = { navController.navigateUp() },
                    onClickEdit = { navController.navigate(Screens.Edit.route) }
                )
            }
            composable(Screens.Edit.route) {
                EditWant(
                    onClickBack = { navController.navigateUp() },
                    onClickChange = { navController.navigate(Screens.Home.route) }
                )
            }
        }
    )
}

sealed class Screens(
    val route: String
) {
    object Home: Screens("home")
    object Add: Screens("add")
    object Preview: Screens("preview")
    object Edit: Screens("edit")
}