package com.kiteworks.kiteworkskmmpoc.data.folder.entities

import kotlinx.serialization.Serializable

@Serializable
data class FolderRemoteEntity(
    val userId: Int,
    val isShared: Boolean,
    val path: String,
    val parentId: Int,
    val type: String,
    val permalink: String,
    val created: String,
    val modified: String,
    val name: String,
    val id: Int,
)
