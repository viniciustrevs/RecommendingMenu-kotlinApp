package com.pi.recommendingmenu.recipes.domain

import com.pi.recommendingmenu.core.domain.util.NetworkError
import com.pi.recommendingmenu.core.domain.util.Result
import com.pi.recommendingmenu.recipes.data.networking.dto.RecipeDto

interface RecipeDataSource {
    suspend fun getRecipes(ingredients: List<String>, model: String): Result<List<RecipeDto>, NetworkError>
}