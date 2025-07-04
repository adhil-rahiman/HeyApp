package com.droidnotes.heyapp

import androidx.compose.runtime.Composable
import com.droidnotes.heyapp.di.appModule
import com.droidnotes.heyapp.ui.screens.HeyHomeWithNav
import com.droidnotes.heyapp.ui.theme.HeyTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    HeyTheme {
        KoinApplication(application = {
            modules(
                modules = appModule
            )
        }) {
            HeyHomeWithNav()
        }
    }
}