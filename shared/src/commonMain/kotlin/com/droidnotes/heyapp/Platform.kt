package com.droidnotes.heyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform