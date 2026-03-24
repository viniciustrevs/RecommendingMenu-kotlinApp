package com.pi.recommendingmenu.recipes.presentation.on_boarding

import androidx.compose.runtime.Immutable

@Immutable
data class OnBoardingState(
    val ingredients: List<String> = emptyList<String>(),
    val selectedIngredients: Set<String> = emptySet(),
    val models: List<String> = emptyList<String>(),
    val selectedModels: Set<String> = emptySet(),
    val isModelButtonEnabled: Boolean = false,
    val isIngredientButtonEnabled: Boolean = false
)
