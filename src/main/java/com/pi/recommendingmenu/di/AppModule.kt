package com.pi.recommendingmenu.di

import com.pi.recommendingmenu.core.data.networking.HttpClientFactory
import com.pi.recommendingmenu.recipes.data.networking.RemoteRecipeDataSource
import com.pi.recommendingmenu.recipes.domain.RecipeDataSource
import com.pi.recommendingmenu.recipes.presentation.on_boarding.OnBoardingViewModel
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteRecipeDataSource).bind<RecipeDataSource>()

    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::RecipesViewModel)

    // TODO:
    //  3. Implement the Data Layer: RemoteDataSource and DataSource
    //  4. Implement the DI module configuration

}