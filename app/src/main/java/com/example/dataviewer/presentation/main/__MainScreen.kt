package com.example.dataviewer.presentation.main

//@Composable
//fun MainScreen(
//    modifier: Modifier = Modifier,
//    state: MainUiState,
//    onHomeScreen: () -> Unit = {},
//    onProjectsScreen: () -> Unit = {},
//    onFeedsScreen: () -> Unit = {}
//) {
//
//    val navController = rememberNavController()
//
//    Scaffold (
//        topBar = {
//            TopMenuNavigation(
//                onHomeClick = onHomeScreen,
//                onFeedsClick = onFeedsScreen,
//                onProjectsClick = onProjectsScreen
//            )
//            when (state.screen) {
//
//                Screens.HOME -> {
//                    HomeScreen()
//                }
//                Screens.SCANNING -> {
//                    ScanningScreen()
//                }
//                Screens.SETTINGS -> {
//
//                }
//
//                Screens.PROJECTS -> {
//                    ProjectsScreen()
//                }
//
//                Screens.FEEDS -> {
//                    FeedsScreen()
//                }
//            }
//        },
//        bottomBar =  {
//            NavigationBar {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination
//                listOfNavItems.forEach { navItem ->
//                    NavigationBarItem(
//                        selected = currentDestination?.hierarchy?.any {it.route == navItem.route } == true,
//                        onClick = {
//                            navController.navigate(navItem.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                      },
//                        icon = { Icon(imageVector = navItem.icon, contentDescription = null ) },
//                        label = { Text(text = stringResource(id = navItem.label)) }
//                    )
//                }
//            }
//        }
//    ) { paddingValues ->
//        NavHost(
//            modifier = Modifier.padding(paddingValues),
//            navController = navController,
//            startDestination = Screens.HOME.name
//        ){
//            composable(route = Screens.HOME.name) { HomeScreen() }
//            composable(route = Screens.SCANNING.name) { ScanningScreen() }
//            composable(route = Screens.SETTINGS.name) {}
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    MainScreen(state = MainUiState(screen = Screens.HOME))
//}