package com.pi.recommendingmenu.recipes.presentation.on_boarding

sealed interface OnBoardingActions {
    data class OnToggleIngredient(
        val ingredient: String,
        val isSelected: Boolean
    ) : OnBoardingActions
    data class OnToggleModel(
        val model: String,
        val isSelected: Boolean
    ) : OnBoardingActions
    data object ValidateIngredientSelection: OnBoardingActions
    data object ValidateModelSelection : OnBoardingActions
}