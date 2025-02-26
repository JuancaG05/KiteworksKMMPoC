package com.kiteworks.kiteworkskmmpoc.data.folder.entities

import kotlinx.serialization.Serializable

@Serializable
data class FolderResponseRemoteEntity(
    val data: List<FolderRemoteEntity>,
)
