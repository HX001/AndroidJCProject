package com.practice.androidprojectjcbased.ActivityThree

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.practice.androidprojectjcbased.ActivityThree.ui.theme.AndroidProjectJCBasedTheme
import com.practice.androidprojectjcbased.ActivityThree.viewModel.ActivityViewModel
import com.practice.androidprojectjcbased.Screen

/*
* This activity is consist of 3 screens:
* 1. Input screen, user type url
* 2. Top part show HTML source from web url, bottom part show web
* 3. Search bar on the top, buttom part show the filter result from URL
* */
class MainActivityThree : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidProjectJCBasedTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp, bottom = 50.dp)
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Photo") {
        navigation(
            startDestination = Screen.InputScreen.route,
            route = "Photo"
        ) {
            composable(
                route = Screen.InputScreen.route
            ) {
                val viewModel = it.sharedViewModel<ActivityViewModel>(navController)
                InputScreen(navController = navController, viewModel = viewModel)
            }

            composable(Screen.ContentListScreen.route) {
                val viewModel = it.sharedViewModel<ActivityViewModel>(navController)
                ListScreen(navController = navController, viewModel = viewModel)
            }

            composable(Screen.FilterScreen.route) {
                val viewModel = it.sharedViewModel<ActivityViewModel>(navController)
                FilterScreen(navController = navController, viewModel = viewModel)
            }
        }

        navigation(
            startDestination = "login",
            route = "auth"
        ) {
            composable("login") {
                Button(
                    onClick = {
                        // navigation to other nested navigation graph
                        navController.navigate("calender") {
                            // pop up to nav graph "auth' from back stack
                            popUpTo("auth") {
                                inclusive = true // also pop up 'auth' navGraph
                            }
                        }
                    }
                ) {

                }
            }
        }

        navigation(
            startDestination = "calendar_overview",
            route = "calender"
        ) {

        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val viewModel = viewModel<ActivityViewModel>()
    NavHost(navController = navController, startDestination = Screen.InputScreen.route) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Top Bar",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultView() {
    MyApp()
}