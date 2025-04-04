package com.kiteworks.kiteworkskmmpoc.dependencyinjection

import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.FolderLocalDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.FolderRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderLocalDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.repository.FolderRepository
import com.kiteworks.kiteworkskmmpoc.data.login.datasources.ILoginRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.data.login.datasources.LoginRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.data.login.repository.LoginRepository
import com.kiteworks.kiteworkskmmpoc.db.KiteworksKMMPoCDB
import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository
import com.kiteworks.kiteworkskmmpoc.domain.folder.usecases.GetAllFoldersUseCase
import com.kiteworks.kiteworkskmmpoc.domain.folder.usecases.RefreshFoldersUseCase
import com.kiteworks.kiteworkskmmpoc.domain.login.repository.ILoginRepository
import com.kiteworks.kiteworkskmmpoc.domain.login.usecases.GetAccessTokenUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedCommonModule = module {
    factoryOf(::GetAllFoldersUseCase)
    factoryOf(::RefreshFoldersUseCase)
    factoryOf(::FolderRepository) bind IFolderRepository::class
    factoryOf(::FolderLocalDataSource) bind IFolderLocalDataSource::class
    factoryOf(::FolderRemoteDataSource) bind IFolderRemoteDataSource::class
    single { KiteworksKMMPoCDB(get()) }

    factoryOf(::GetAccessTokenUseCase)
    factoryOf(::LoginRepository) bind ILoginRepository::class
    factoryOf(::LoginRemoteDataSource) bind ILoginRemoteDataSource::class
}
