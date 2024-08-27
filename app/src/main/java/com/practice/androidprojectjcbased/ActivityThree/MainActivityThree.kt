package com.practice.androidprojectjcbased.ActivityThree

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = { TopAppBar(scrollBehavior = scrollBehavior) },
//    ) {
//        Surface(
//            modifier = Modifier.fillMaxSize()
//        ) {
//
//        }
//    }
    HomeScreen()
}

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val viewModel = viewModel<ActivityViewModel>()
    NavHost(navController = navController, startDestination = Screen.InputScreen.route) {
        composable(
            route = Screen.InputScreen.route
        ) {
            InputScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.ContentListScreen.route) {
            ListScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.FilterScreen.route) {
            FilterScreen(navController = navController, viewModel = viewModel)
        }
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