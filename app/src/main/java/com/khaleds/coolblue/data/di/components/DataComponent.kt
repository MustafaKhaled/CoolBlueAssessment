package com.khaleds.coolblue.data.di.components

import com.khaleds.coolblue.data.di.modules.RepoModule
import com.khaleds.coolblue.data.di.scopes.DataComponentScope
import com.khaleds.coolblue.di.component.ApplicationComponent
import com.khaleds.coolblue.domain.repo.ProductsRepository
import dagger.Component
@DataComponentScope
@Component(dependencies = [ApplicationComponent::class], modules = [RepoModule::class])
interface DataComponent {
    fun provideRepository(): ProductsRepository
}