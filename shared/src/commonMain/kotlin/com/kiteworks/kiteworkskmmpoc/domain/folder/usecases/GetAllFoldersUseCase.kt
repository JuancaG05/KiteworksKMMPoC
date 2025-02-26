package com.kiteworks.kiteworkskmmpoc.domain.folder.usecases

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository

class GetAllFoldersUseCase(
    private val folderRepository: IFolderRepository
) {

    suspend fun execute(): List<Folder> =
        folderRepository.getAllFolders()

}
