package com.khaleds.coolblue.presentation.di.modules

import com.khaleds.coolblue.domain.usecases.allproducts.AllProductsUseCase
import com.khaleds.coolblue.domain.usecases.allproducts.IProductUseCase
import com.khaleds.coolblue.presentation.di.scope.PresentationScope
import dagger.Binds
import dagger.Module

@Module
interface IProductsUseCaseModule {
    @PresentationScope
    @Binds
    fun bindsMoviesParsable(allProductsUseCase: AllProductsUseCase): IProductUseCase

}