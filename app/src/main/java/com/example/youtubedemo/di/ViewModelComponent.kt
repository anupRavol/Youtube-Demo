package com.example.youtubedemo.di

import com.example.youtubedemo.PopularListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {
    fun inject(viewModel : PopularListViewModel)
}