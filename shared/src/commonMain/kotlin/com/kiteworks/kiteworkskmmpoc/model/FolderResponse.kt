package com.kiteworks.kiteworkskmmpoc.model

import kotlinx.serialization.Serializable

// This is the response of the API
@Serializable
data class FolderResponse(
    val data: List<Folder>,
)
