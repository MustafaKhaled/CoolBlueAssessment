package com.khaleds.coolblue.ui.details.di.component

import com.khaleds.coolblue.presentation.di.component.PresentationComponent
import com.khaleds.coolblue.ui.details.ProductDetailsFragment
import com.khaleds.coolblue.ui.home.HomeFragment
import com.khaleds.coolblue.ui.home.di.scope.AllProductsScope
import dagger.Component
@AllProductsScope
@Component(dependencies = [PresentationComponent::class])
interface ProductDetailsComponent {
    fun inject(detailsFragment: ProductDetailsFragment)
}