package com.practice.androidprojectjcbased.ActivityThree

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.practice.androidprojectjcbased.ActivityThree.viewModel.ActivityViewModel
import com.practice.androidprojectjcbased.Screen

@Composable
fun FilterScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ActivityViewModel,
) {
    var query by remember {
        mutableStateOf("")
    }

    val searchQuery by viewModel.searchText.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        TextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchTextChange,
            label = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp)
        )

        val filteredPhotos = viewModel.searchPosts(searchQuery)
        Column (
            modifier = Modifier
                .weight(1f, false)
        ) {
            ListContent(photos = filteredPhotos)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.ContentListScreen.route)
            }
        ) {
            Text("Go back to previous")
        }
    }
}