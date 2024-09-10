package com.practice.androidprojectjcbased.CountWithFlow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CountViewModel: ViewModel() {
    val countUpFlow = flow<Int> {
        val startingValue = 0
        var currentValue = startingValue
        while (true) {
            delay(1000L)
            currentValue++
            emit(currentValue)
        }
    }

}