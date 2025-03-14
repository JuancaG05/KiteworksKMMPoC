package com.kiteworks.kiteworkskmmpoc.data.folder.entities

import kotlinx.serialization.Serializable

@Serializable
data class FolderRemoteEntity(
    val id: String,
    val name: String,
    val userId: String,
    val isShared: Boolean,
    val path: String,
    val parentId: String,
    val type: String,
    val permalink: String,
    val created: String,
    val modified: String,
)
