package com.kiteworks.kiteworkskmmpoc.data.folder

import com.kiteworks.kiteworkskmmpoc.db.FolderLocalEntity
import com.kiteworks.kiteworkskmmpoc.db.KiteworksKMMPoCDB
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder

class FolderLocalDataSource(database: KiteworksKMMPoCDB): IFolderLocalDataSource {

    private val queries = database.folderQueries

    override suspend fun getAllFolders(): List<Folder> =
        queries.getAllFolders().executeAsList().map { it.toModel() }

    fun FolderLocalEntity.toModel(): Folder =
        Folder(
            id = id.toInt(),
            name = name,
            userId = userId.toInt(),
            isShared = isShared == 1L,
            path = path,
            parentId = parentId.toInt(),
            type = type,
            permalink = permalink,
            created = created,
            modified = modified,
        )
}
