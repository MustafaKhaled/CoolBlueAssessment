package com.khaleds.coolblue.data.di.modules

import com.khaleds.coolblue.data.di.scopes.DataComponentScope
import com.khaleds.coolblue.data.repo.ProductsRepositoryImpl
import com.khaleds.coolblue.domain.repo.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @DataComponentScope
    @Binds
    fun bindsMoviesParsable(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

}