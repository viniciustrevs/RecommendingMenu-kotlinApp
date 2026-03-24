package com.pi.recommendingmenu.recipes.presentation.recipe_list

import androidx.compose.runtime.Immutable
import com.pi.recommendingmenu.recipes.presentation.models.RecipeUI

@Immutable
data class RecipesState(
    val isLoading: Boolean = false,
    val recipes: List<RecipeUI> = emptyList(),
)
