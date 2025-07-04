package com.droidnotes.heyapp.model

data class Contact(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val isCloseFriend: Boolean = false
)
