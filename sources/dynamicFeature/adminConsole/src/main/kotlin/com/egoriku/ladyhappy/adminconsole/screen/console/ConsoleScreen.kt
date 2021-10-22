package com.egoriku.ladyhappy.adminconsole.screen.console

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.egoriku.ladyhappy.adminconsole.screen.NavScreen
import com.egoriku.ladyhappy.adminconsole.screen.console.ui.AdminFeatureItem

@Composable
fun ConsoleScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        AdminFeatureItem(
            onClick = { navController.navigate(NavScreen.PublishNewsScreen.route) },
            name = "Publish news"
        )
        AdminFeatureItem(
            onClick = { navController.navigate(NavScreen.PublishProductScreen.route) },
            name = "Publish product"
        )
        AdminFeatureItem(
            onClick = { navController.navigate(NavScreen.ManageUsersScreen.route) },
            name = "Manage users and permissions"
        )
    }
}