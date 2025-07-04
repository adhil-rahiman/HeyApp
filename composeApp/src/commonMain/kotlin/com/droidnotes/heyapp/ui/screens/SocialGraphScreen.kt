package com.droidnotes.heyapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.droidnotes.heyapp.presentation.GraphViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SocialGraphScreen(viewModel: GraphViewModel = koinViewModel() ) {
    val contacts by viewModel.contacts.collectAsState()

    ContactGraph(contacts)
}
