package com.practice.androidprojectjcbased.filterResult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FilterFeatureViewModule: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(allPersons)
    val persons = searchText
//        .debounce(1000L) // wait 1s before execute the following block
        .onEach { _isSearching.update { true } }// show the progress bar if still execute combine block
        .combine(_persons) { text, persons ->
            if(text.isBlank()) {
                persons
            } else {
                delay(2000L)
                persons.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn( // convert normal flow to state flow
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

data class Person(
    val firstName: String,
    val lastName: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allPersons = listOf(
    Person(
        firstName = "Jason",
        lastName = "Anderson"
    ),
    Person(
        firstName = "Jeff",
        lastName = "Bezos"
    ),
    Person(
        firstName = "Chris P.",
        lastName = "Bacon"
    ),
    Person(
        firstName = "Steve",
        lastName = "Jobs"
    ),
    Person(
        firstName = "Peter",
        lastName = "Park"
    ),
)