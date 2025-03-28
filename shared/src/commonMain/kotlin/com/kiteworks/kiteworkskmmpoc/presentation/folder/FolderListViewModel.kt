package com.kiteworks.kiteworkskmmpoc.presentation.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import com.kiteworks.kiteworkskmmpoc.domain.folder.usecases.GetAllFoldersUseCase
import com.kiteworks.kiteworkskmmpoc.domain.folder.usecases.RefreshFoldersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FolderListViewModel (
    private val refreshFoldersUseCase: RefreshFoldersUseCase,
    getAllFoldersUseCase: GetAllFoldersUseCase,
): ViewModel() {

    val listOfFolders: StateFlow<List<Folder>> =
        getAllFoldersUseCase.execute().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun refreshFolders(serverUrl: String, accessToken: String) {
        viewModelScope.launch {
            refreshFoldersUseCase.execute(serverUrl, accessToken)
        }
    }

}
