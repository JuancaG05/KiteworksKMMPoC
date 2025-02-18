package com.kiteworks.kiteworkskmmpoc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform