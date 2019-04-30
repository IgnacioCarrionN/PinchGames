package dev.carrion.pinchgames.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import androidx.paging.toLiveData
import dev.carrion.pinchgames.data.local.game.GamesDao
import dev.carrion.pinchgames.data.mappers.toDomain
import dev.carrion.pinchgames.domain.DomainEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LocalDataSource(val gamesDao: GamesDao): CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    // Get games list as DataSource from Paging library
    fun getGames(): DataSource.Factory<Int, DomainEntity.GameShort> {
        val data = gamesDao.selectAll()

        // Return converted data from db to DomainEntity.GameShort
        return data.map {
            DomainEntity.GameShort(
                id = it.id,
                name = it.name,
                cover = it.cover
            )
        }
    }


    // Get game info given an ID as DomainEntity.Game type
    fun getGameInfo(id: Long): LiveData<DomainEntity.Game> {
        // Get games from db in LiveData<DataEntity.Game> format
        val gamesDB = gamesDao.select(id)

        // Create mediator of DomainEntity.Game type
        val mediator = MediatorLiveData<DomainEntity.Game>()

        // Add source from gamesDb and convert to DomainEntity.Game
        mediator.addSource(gamesDB) {
            val gameDomain = it.toDomain()
            mediator.value = gameDomain
        }

        return mediator
    }

    fun insertGames(gamesList: List<LocalDataEntity.Game>, insertFinished: () -> Unit) {
        launch {
            gamesDao.insert(gamesList)
            insertFinished()
        }
    }

    fun updateGames(){
        Log.d("LocalDataSource", "updateGames")
        launch {
            gamesDao.truncate()
        }
    }
}