package com.kiteworks.kiteworkskmmpoc.dependencyinjection

import app.cash.sqldelight.db.SqlDriver
import com.kiteworks.kiteworkskmmpoc.data.DatabaseDriverFactory
import org.koin.dsl.module

val sharedAndroidModule = module {
    single<SqlDriver> { DatabaseDriverFactory(get()).createDriver() }
}
