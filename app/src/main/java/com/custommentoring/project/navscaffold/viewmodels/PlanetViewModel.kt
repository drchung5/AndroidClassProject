package com.custommentoring.project.navscaffold.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.custommentoring.project.navscaffold.data.Movie
import com.custommentoring.project.navscaffold.data.MovieWrapper
import com.custommentoring.project.navscaffold.data.Planet
import com.custommentoring.project.navscaffold.data.PlanetWrapper
import com.custommentoring.project.navscaffold.services.MovieService
import com.custommentoring.project.navscaffold.services.PlanetService
import kotlinx.coroutines.launch

class PlanetViewModel : ViewModel() {

    private val _planetWrapper : MutableState<PlanetWrapper> =
        mutableStateOf(PlanetWrapper(0,null,null,emptyList<Planet>()))

    val planetWrapper get() = _planetWrapper

    fun getPlanets() {
        viewModelScope.launch {
            val planetService = PlanetService.getInstance()
            try {
                _planetWrapper.value = planetService.getPlanets()
            } catch (e: Exception) {

            }
        }
    }


}