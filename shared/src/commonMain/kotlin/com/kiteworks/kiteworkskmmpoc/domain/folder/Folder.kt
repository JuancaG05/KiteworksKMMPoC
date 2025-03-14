package com.kiteworks.kiteworkskmmpoc.domain.folder

import kotlinx.serialization.Serializable

@Serializable
data class Folder(
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
