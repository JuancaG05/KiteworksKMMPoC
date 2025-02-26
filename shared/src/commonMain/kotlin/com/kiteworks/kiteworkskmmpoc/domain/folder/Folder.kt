package com.kiteworks.kiteworkskmmpoc.domain.folder

import kotlinx.serialization.Serializable

@Serializable
data class Folder(
    val id: Int,
    val name: String,
    val userId: Int,
    val isShared: Boolean,
    val path: String,
    val parentId: Int,
    val type: String,
    val permalink: String,
    val created: String,
    val modified: String,
)
