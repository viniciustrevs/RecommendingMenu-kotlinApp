package com.pi.recommendingmenu.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesScreenRoot
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesViewModel
import com.pi.recommendingmenu.recipes.presentation.on_boarding.OnBoardingScreenRoot
import com.pi.recommendingmenu.recipes.presentation.on_boarding.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.WelcomeScreen,
    ) {
        composable<Route.WelcomeScreen> {
            val viewModel = koinViewModel<OnBoardingViewModel>()

            OnBoardingScreenRoot(
                viewModel = viewModel,
                onNavigate = { ingredients, model ->
                    navController.navigate(
                        Route.RecipeList(ingredients, model)
                    )
                }
            )
        }

        composable<Route.RecipeList> {
            val viewModel = koinViewModel<RecipesViewModel>()

            RecipesScreenRoot(
                viewModel = viewModel,
                onBackClick = { navController.navigate(Route.WelcomeScreen) },
            )
        }
    }
}

