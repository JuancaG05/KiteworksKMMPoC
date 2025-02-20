package com.kiteworks.kiteworkskmmpoc.model

import kotlinx.serialization.Serializable

@Serializable
data class Creator(
    val email: String,
    val profileIcon: String,
    val name: String,
    val id: Int
)
