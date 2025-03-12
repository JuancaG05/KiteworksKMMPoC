package com.kiteworks.kiteworkskmmpoc.android.dependencyinjection

import com.kiteworks.kiteworkskmmpoc.android.presentation.folder.FolderListViewModel
import com.kiteworks.kiteworkskmmpoc.android.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    viewModelOf(::FolderListViewModel)
    viewModelOf(::LoginViewModel)
}
