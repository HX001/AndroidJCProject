package com.practice.androidprojectjcbased.ActivityThree

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.practice.androidprojectjcbased.ActivityThree.data.Photos
import com.practice.androidprojectjcbased.ActivityThree.viewModel.ActivityViewModel
import com.practice.androidprojectjcbased.R
import com.practice.androidprojectjcbased.Screen

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ActivityViewModel
) {

//    LaunchedEffect(true) {
//        viewModel.fetchPosts()
//    }

    val photos by viewModel.posts.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .weight(1f, false)
        ) {
            ListContent(photos = photos)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.FilterScreen.route)
            }
        ) {
            Text("Go to Search Screen")
        }
    }
}

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    photos: List<Photos>
) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = modifier.padding(horizontal = 4.dp),
            contentPadding = contentPadding,
        ) {
            items(items = photos, key = { photo -> photo.id }) { photo ->
                PhotoCard(
                    photo,
                    modifier = modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoCard(photo: Photos, modifier: Modifier) {
    val context = LocalContext.current
    Surface {
        Column(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        ) {
            Card(
                modifier = modifier,
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                onClick = { Toast.makeText(context, "Click on item ${photo.id}", Toast.LENGTH_SHORT).show()}
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current).data(photo.url)
                        .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = "Photo Place Holder",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = photo.title,
                style = MaterialTheme.typography.bodySmall
            )
            Divider(modifier = Modifier.padding(horizontal = 5.dp))
        }

    }
}

