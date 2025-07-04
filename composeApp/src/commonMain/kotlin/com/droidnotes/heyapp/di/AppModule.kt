package com.droidnotes.heyapp.di

import com.droidnotes.heyapp.presentation.GraphViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { GraphViewModel() }
}
