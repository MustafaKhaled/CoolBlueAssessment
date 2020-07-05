package com.khaleds.coolblue.presentation.di.modules

import com.khaleds.coolblue.data.di.scopes.DataComponentScope
import com.khaleds.coolblue.data.repo.ProductsRepositoryImpl
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.khaleds.coolblue.domain.usecases.AllProductsUseCase
import com.khaleds.coolblue.domain.usecases.IProductUseCase
import com.khaleds.coolblue.presentation.di.scope.PresentationScope
import dagger.Binds
import dagger.Module

@Module
interface IProductsUseCaseModule {
    @PresentationScope
    @Binds
    fun bindsMoviesParsable(allProductsUseCase: AllProductsUseCase): IProductUseCase

}