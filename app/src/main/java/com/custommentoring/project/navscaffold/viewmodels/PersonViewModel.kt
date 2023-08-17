package com.custommentoring.project.navscaffold.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.custommentoring.project.navscaffold.data.Person
import com.custommentoring.project.navscaffold.services.PeopleService
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import com.custommentoring.project.navscaffold.data.PeopleWrapper

class PersonViewModel : ViewModel() {

    private val _peopleWrapper :MutableState<PeopleWrapper> =
        mutableStateOf(PeopleWrapper(0,null,null,emptyList<Person>()))

    val peopleWrapper get() = _peopleWrapper

    fun getPeople() {
        viewModelScope.launch {
            val peopleService = PeopleService.getInstance()
            try {
                _peopleWrapper.value = peopleService.getPeople()
            } catch (e: Exception) {

            }
        }
    }

}
