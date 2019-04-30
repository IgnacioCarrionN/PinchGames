package dev.carrion.pinchgames.di

import androidx.room.Room
import dev.carrion.pinchgames.data.Repository
import dev.carrion.pinchgames.data.local.LocalDataSource
import dev.carrion.pinchgames.data.local.PinchGamesDatabase
import dev.carrion.pinchgames.data.network.NetworkDataSource
import dev.carrion.pinchgames.data.network.PinchGamesApi
import dev.carrion.pinchgames.ui.gamedetails.GameDetailsViewModel
import dev.carrion.pinchgames.ui.mainlist.MainListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {


    single { PinchGamesApi.create() }

    single {
        Room.databaseBuilder(
            androidContext(),
            PinchGamesDatabase::class.java,
            "pinchgames").fallbackToDestructiveMigration().build()
    }

    single { get<PinchGamesDatabase>().gamesDao() }

    single { LocalDataSource(get()) }

    single { NetworkDataSource(get()) }

    single { Repository(get(),get()) }

    viewModel { MainListViewModel(get()) }

    viewModel { GameDetailsViewModel(get(),getProperty("id")) }

}