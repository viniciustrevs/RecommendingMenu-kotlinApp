package com.pi.recommendingmenu.recipes.presentation.recipe_list

sealed interface RecipeAction {
    data object OnLoadNextRecipe : RecipeAction
    data object OnBackClick : RecipeAction
}