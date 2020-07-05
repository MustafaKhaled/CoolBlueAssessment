package com.khaleds.coolblue.presentation

import androidx.lifecycle.ViewModel
import com.khaleds.coolblue.presentation.di.scope.PresentationScope
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@PresentationScope
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)