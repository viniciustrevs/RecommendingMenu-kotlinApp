package com.pi.recommendingmenu.recipes.presentation.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OnBoardingViewModel(
): ViewModel() {

    val uniqueIngredients = listOf<String>(
        "carne de boi", "queijo", "ovo", "mostarda", "pimenta jalapeno", "nirá",
        "carne de peixe", "repolho", "abacate", "tomate", "limão", "picles", "cebola", "coentro",
        "camarão", "ciboullete", "tortilla", "milho", "carne de porco", "acelga", "nabo",
        "couve-flor", "feijão", "grão de bico", "abóbora", "alho", "abacaxi", "canela", "salsa",
        "cogumelo", "castanha do pará", "nozes", "gergilim", "pimentão", "trigo", "carne de frango"
    )

    val uniqueModels = listOf<String>("KNN", "Random Forest", "Naive Bayes")

    private val _state = MutableStateFlow(
        OnBoardingState(
            ingredients = uniqueIngredients,
            models = uniqueModels
        )
    )

    val state = _state
        .onStart {}
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000L), _state.value)

    fun onAction(action: OnBoardingActions) {
        when (action) {

            is OnBoardingActions.OnToggleIngredient -> {
                when(action.isSelected) {
                    true -> {
                        _state.update { it.copy(selectedIngredients = it.selectedIngredients - action.ingredient) }
                    }
                    false -> {
                        _state.update { it.copy(selectedIngredients = it.selectedIngredients + action.ingredient) }
                    }

                }
            }

            is OnBoardingActions.OnToggleModel -> {
                when(action.isSelected) {
                    true -> {
                        _state.update { it.copy(selectedModels = it.selectedModels - action.model) }
                    }
                    false -> {
                        _state.update { it.copy(selectedModels = it.selectedModels + action.model) }
                    }
                }
            }

            is OnBoardingActions.ValidateIngredientSelection -> {
                val isIngredientButtonEnabled = _state.value.selectedIngredients.size == 5
                _state.update { it.copy(isIngredientButtonEnabled = isIngredientButtonEnabled) }
            }

            is OnBoardingActions.ValidateModelSelection -> {
                val isModelButtonEnabled = _state.value.selectedModels.isNotEmpty()
                _state.update { it.copy(isModelButtonEnabled = isModelButtonEnabled) }
            }
        }
    }
}