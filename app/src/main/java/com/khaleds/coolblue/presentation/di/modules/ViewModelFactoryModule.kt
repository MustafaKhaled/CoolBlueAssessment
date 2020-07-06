package com.khaleds.coolblue.presentation.di.modules

import androidx.lifecycle.ViewModel
import com.khaleds.coolblue.presentation.ViewModelKey
import com.khaleds.coolblue.presentation.di.scope.PresentationScope
import com.khaleds.coolblue.presentation.viewmodels.AllProductsViewModel
import com.khaleds.coolblue.presentation.viewmodels.ProductDetailsViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllProductsViewModel::class)
    @PresentationScope
    fun bindAllProductsViewModel(allProductsViewModel: AllProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    @PresentationScope
    fun bindAllProductsViewModel(productDetailsViewModel: ProductDetailsViewModel): ViewModel



}