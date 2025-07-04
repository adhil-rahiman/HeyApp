package com.droidnotes.heyapp.presentation

import androidx.lifecycle.ViewModel
import com.droidnotes.heyapp.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GraphViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    init {
        loadMockContacts()
    }

    private fun loadMockContacts() {
        _contacts.value = listOf(
            Contact(id = "1", name = "Kayu", isCloseFriend = true),
            Contact(id = "2", name = "JLK"),
            Contact(id = "3", name = "Biju"),
            Contact(id = "4", name = "Adhil", isCloseFriend = true),
        )
    }
}
