package com.kiteworks.kiteworkskmmpoc.model

import kotlinx.serialization.Serializable

@Serializable
data class Folder(
    val maxFileLifetime: Int,
    val userId: Int,
    val isShared: Boolean,
    val path: String,
    val parentId: Int,
    val useFolderQuota: Boolean,
    val maxFolderExpiration: Int,
    val secure: Boolean,
    val type: String,
    val permalink: String,
    val created: String,
    val modified: String,
    val syncable: Boolean,
    val fileLifetime: Int,
    val name: String,
    val expire: Int,
    val permDeleted: Boolean,
    val creator: Creator,
    val id: Int,
    val description: String?,
    val source: String?,
    val deleted: Boolean
)
