package com.practice.androidprojectjcbased.ActivityThree.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.androidprojectjcbased.ActivityThree.data.Photos
import com.practice.androidprojectjcbased.ActivityThree.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ActivityViewModel: ViewModel() {

    private val TAG = "ActivityViewModel"

    private var _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private var _posts = MutableStateFlow<List<Photos>>(emptyList())
    var posts = _posts.asStateFlow()

    fun onSearchTextChange(text: String) {
        Log.i(TAG, "Search Text changed: ${text}")
        _searchText.value = text
    }

    fun fetchPosts() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getPhotoContent()
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
              _posts.value = response.body()!!
            } else {
                Log.e(TAG, "Response Error")
            }
        }
    }

    fun searchPosts(query: String): List<Photos> {
        val filteredPosts = _posts.value.filter {
            it.title.contains(query, ignoreCase = true)
        }
        Log.i(TAG, "Query: '$query' - Filtered Posts: ${filteredPosts.size}")
        return filteredPosts
    }
}