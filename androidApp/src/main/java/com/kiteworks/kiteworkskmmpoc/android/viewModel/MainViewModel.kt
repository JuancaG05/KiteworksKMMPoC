package com.kiteworks.kiteworkskmmpoc.android.viewModel


import com.kiteworks.kiteworkskmmpoc.KtorClient
import com.kiteworks.kiteworkskmmpoc.domain.FolderResponse

class MainViewModel (
    private val ktorClient: KtorClient
){

    suspend fun getFolders(): FolderResponse = ktorClient.getFolders()
}
