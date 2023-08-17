package com.custommentoring.project.navscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.custommentoring.project.navscaffold.pages.MoviePage
import com.custommentoring.project.navscaffold.pages.PeoplePage
import com.custommentoring.project.navscaffold.pages.PlanetPage
import com.custommentoring.project.navscaffold.ui.theme.NavScaffoldTheme
import com.custommentoring.project.navscaffold.viewmodels.MovieViewModel
import com.custommentoring.project.navscaffold.viewmodels.PersonViewModel
import com.custommentoring.project.navscaffold.viewmodels.PlanetViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppUI()
                }
            }
        }
    }
}

@Composable
fun MyAppUI() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navHostController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text(stringResource(R.string.app_name))},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                        contentDescription = "Menu", modifier = Modifier.clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                },
            backgroundColor = MaterialTheme.colors.primary)
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            MyFloatingActionButton( onNavigateBack = { navHostController.popBackStack() } )
        },
        drawerContent = {
            MyDrawer(
              onDrawerClose = {
                  scope.launch { scaffoldState.drawerState.close() }
              },
              listOf(
                  NavItem(stringResource(R.string.people)) {
                      navHostController.navigate("people") },
                  NavItem(stringResource(R.string.planets)) { navHostController.navigate("planets") },
                  NavItem(stringResource(R.string.movies)) { navHostController.navigate("movies") }
              )
           )
        },
        content = { MyNavHost( navController = navHostController ) },
        bottomBar = {
            BottomAppBar(backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text(text = stringResource(R.string.copyright),textAlign= TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            } }
    )
}

@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "people"
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        val personViewModel = PersonViewModel()
        composable("people") { PeoplePage(personViewModel) }

        val planetViewModel = PlanetViewModel()
        composable("planets") { PlanetPage(planetViewModel) }

        val movieViewModel = MovieViewModel()
        composable("movies") { MoviePage(movieViewModel) }

    }

}

@Composable
fun MyPage(
    label :String,
    color :Color,
    onNavigateNext: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color)
        ) {
            Text(
                text = label,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Button(onClick = onNavigateNext, modifier = Modifier.padding(5.dp)) {
            Text("Next", fontSize = 20.sp)
        }
    }
}

@Composable
fun MyDrawer(
    onDrawerClose: () -> Unit,
    navItems: List<NavItem>
) {
    Column {
        Box(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
            .padding(10.dp)) {
                Text(
                    text = stringResource(R.string.scaffold_title),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }

        for( navItem in navItems ) {
            Text(
            text = navItem.label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = {
                    navItem.navFunction()
                    onDrawerClose()
                })
            )
        }

    }
}

@Composable
fun MyFloatingActionButton(
    onNavigateBack: () -> Boolean
) {
  FloatingActionButton(onClick = { onNavigateBack() }){
    Icon(
        painter = painterResource(id = R.drawable.arrow_u_left_top_bold),
        contentDescription = "Back"
    )
  }
}

class NavItem( val label :String, val navFunction :() -> Unit )
