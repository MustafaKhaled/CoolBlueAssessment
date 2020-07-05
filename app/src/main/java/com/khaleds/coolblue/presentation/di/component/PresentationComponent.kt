package com.khaleds.coolblue.presentation.di.component

import com.khaleds.coolblue.data.di.components.DataComponent
import com.khaleds.coolblue.presentation.di.modules.ViewModelFactoryModule
import com.khaleds.coolblue.presentation.di.scope.PresentationScope
import com.khaleds.coolblue.presentation.factory.ViewModelFactory
import dagger.Component

@PresentationScope
@Component(modules = [ViewModelFactoryModule::class],dependencies = [DataComponent::class])
interface PresentationComponent {

    fun exposeViewModel(): ViewModelFactory
}