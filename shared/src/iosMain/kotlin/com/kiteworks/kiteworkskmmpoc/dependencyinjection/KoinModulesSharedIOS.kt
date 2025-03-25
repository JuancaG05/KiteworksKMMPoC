package com.kiteworks.kiteworkskmmpoc.dependencyinjection

import app.cash.sqldelight.db.SqlDriver
import com.kiteworks.kiteworkskmmpoc.data.DatabaseDriverFactory
import com.kiteworks.kiteworkskmmpoc.presentation.folder.FolderListViewModel
import com.kiteworks.kiteworkskmmpoc.presentation.login.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val sharedIOSModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    factoryOf(::FolderListViewModel)
    factoryOf(::LoginViewModel)
}
