package com.pi.recommendingmenu.recipes.presentation.recipe_list

interface RecipeEvents {
    data class ShowError(val message: String) : RecipeEvents
}