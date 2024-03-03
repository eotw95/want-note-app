package com.eotw95.wantnote

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eotw95.wantnote.screen.AddWant
import com.eotw95.wantnote.screen.EditWant
import com.eotw95.wantnote.screen.PreviewWant
import com.eotw95.wantnote.screen.WantList
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(
    navController: NavHostController,
    startImageGallery: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        builder = {
            composable(Screens.Home.route) {
                WantList(
                    onClickItem = { itemId, link, desc, path ->
                        val tmpLink = if (link.isEmpty()) KEY_EMPTY else link
                        val tmpDesc = if (desc.isEmpty()) KEY_EMPTY else desc
                        val tmpPath = if (path.isEmpty()) KEY_EMPTY else path

                        val encodeLink =
                            URLEncoder.encode(tmpLink, StandardCharsets.UTF_8.toString())
                        val encodeDesc =
                            URLEncoder.encode(tmpDesc, StandardCharsets.UTF_8.toString())
                        val encodePath =
                            URLEncoder.encode(tmpPath, StandardCharsets.UTF_8.toString())

                        navController.navigate(
                            Screens.Preview.route + "/$itemId/$encodeLink/$encodeDesc/$encodePath"
                        )
                    }
                )
            }
            composable(Screens.Add.route) {
                AddWant(
                    onClickAdd = { navController.navigate(Screens.Home.route) },
                    onClickSetImage = startImageGallery
                )
            }
            composable(Screens.Preview.route + "/{itemId}/{link}/{desc}/{path}") {
                it.arguments?.getString("itemId")?.let { itemId ->
                    it.arguments?.getString("link")?.let { link ->
                        it.arguments?.getString("desc")?.let { desc ->
                            it.arguments?.getString("path")?.let { path ->
                                PreviewWant(
                                    id = itemId.toInt(),
                                    link = link,
                                    description = desc,
                                    imagePath = path,
                                    onClickBack = { navController.navigateUp() },
                                    onClickEdit = { navController.navigate(Screens.Edit.route) },
                                    onClickDelete = { navController.navigate(Screens.Home.route) }
                                )
                            }
                        }
                    }
                }
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