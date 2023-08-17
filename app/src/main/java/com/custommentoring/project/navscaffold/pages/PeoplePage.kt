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
import com.custommentoring.project.navscaffold.viewmodels.PersonViewModel

@Composable
fun PeoplePage( personViewModel: PersonViewModel) {

    LaunchedEffect(Unit, block = {
        personViewModel.getPeople()
    })

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            stringResource(R.string.people),
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        for ( person in personViewModel.peopleWrapper.value.results!!) {
            Text(
                person.name,
                modifier = Modifier.padding(10.dp, 2.dp),
                fontSize = 25.sp
            )
        }
    }
}
