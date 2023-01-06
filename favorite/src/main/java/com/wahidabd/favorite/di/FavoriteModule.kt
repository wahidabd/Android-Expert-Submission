package com.wahidabd.favorite.di

import android.content.Context
import com.wahidabd.core.di.FavoriteModuleDependencies
import com.wahidabd.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.EntryPointAccessors

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteModule {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteModule
    }
}

internal fun FavoriteFragment.inject(){
    DaggerFavoriteModule.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                FavoriteModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
}