package com.kiteworks.kiteworkskmmpoc.domain.login

import kotlinx.serialization.Serializable

@Serializable
data class AccessToken(
    val accessToken: String,
    val refreshToken: String,
)
