package com.pi.recommendingmenu.recipes.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.pi.recommendingmenu.app.Route
import com.pi.recommendingmenu.core.domain.util.Result
import com.pi.recommendingmenu.recipes.data.fake_db.RecipesMap
import com.pi.recommendingmenu.recipes.domain.RecipeDataSource
import com.pi.recommendingmenu.recipes.presentation.models.RecipeUI
import com.pi.recommendingmenu.recipes.presentation.utils.TypeConverter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipesViewModel(
    savedStateHandle: SavedStateHandle,
    private val dataSource: RecipeDataSource
) : ViewModel() {

    private val _ingredients = savedStateHandle.toRoute<Route.RecipeList>().ingredients
    val ingredients: List<String> = TypeConverter.fromStringToList(_ingredients)

    private val _model = savedStateHandle.toRoute<Route.RecipeList>().model
    val model: String = TypeConverter.fromStringToList(_model).first()

    private val _state = MutableStateFlow(
        RecipesState(
            isLoading = true,
        )
    )
    val state = _state.onStart {
        getRecommendedRecipes()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    private val _events = Channel<RecipeEvents>()
    val events = _events.receiveAsFlow()

    fun onAction(action: RecipeAction) {
        when (action) {
            is RecipeAction.OnBackClick -> {

            }

            else -> Unit
        }
    }

    private fun handleSelectedModel(): String = when (model) {
        "KNN" -> "knn"
        "Random Forest" -> "random-forest"
        "Naive Bayes" -> "naive-bayes"
        else -> "knn"
    }

    private fun getRecommendedRecipes() {

        val handledModel = handleSelectedModel()

        viewModelScope.launch {
            when (val res = dataSource.getRecipes(ingredients, handledModel)) {
                is Result.Success -> {

                    val recipeResponse = res.data

                    val recipesUi: List<RecipeUI> = recipeResponse.mapNotNull { recipe ->
                        RecipesMap[recipe.name]
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            recipes = recipesUi
                        )
                    }
                }

                is Result.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    _events.send(
                        RecipeEvents.ShowError("Falha ao carregar receitas")
                    )
                }
            }
        }
    }
}