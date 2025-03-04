package com.kiteworks.kiteworkskmmpoc.domain.folder.usecases

import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository

class RefreshFoldersUseCase(
    private val folderRepository: IFolderRepository
) {

    suspend fun execute() {
        folderRepository.refreshFolders()
    }
}
