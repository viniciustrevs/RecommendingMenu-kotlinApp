package com.pi.recommendingmenu.recipes.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val ingredients: List<String>,
    val name: String,
)