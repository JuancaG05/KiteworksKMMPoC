package com.kiteworks.kiteworkskmmpoc.model

import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    val total: Int,
    val totalDeletedFilesCount: Int,
    val totalDeletedFoldersCount: Int,
    val limit: Int?,
    val offset: Int?
)
