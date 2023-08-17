package com.custommentoring.project.navscaffold.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.custommentoring.project.navscaffold.R
import com.custommentoring.project.navscaffold.viewmodels.PlanetViewModel

@Composable
fun PlanetPage(planetViewModel: PlanetViewModel) {

    LaunchedEffect(Unit, block = {
        planetViewModel.getPlanets()
    })

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            stringResource(R.string.planets),
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        for ( planet in planetViewModel.planetWrapper.value.results!!) {
            Text(
                planet.name,
                modifier = Modifier.padding(10.dp, 2.dp),
                fontSize = 25.sp
            )
        }
    }

}
