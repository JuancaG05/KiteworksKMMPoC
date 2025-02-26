package com.kiteworks.kiteworkskmmpoc.android.viewModel


import com.kiteworks.kiteworkskmmpoc.KtorClient
import com.kiteworks.kiteworkskmmpoc.data.folder.entities.FolderResponseRemoteEntity

class MainViewModel (
    private val ktorClient: KtorClient
){

    suspend fun getFolders(): FolderResponseRemoteEntity = ktorClient.getFolders()
}
