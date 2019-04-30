package dev.carrion.pinchgames.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.carrion.pinchgames.data.local.LocalDataSource
import dev.carrion.pinchgames.data.mappers.toLocalData
import dev.carrion.pinchgames.data.network.NetworkDataSource
import dev.carrion.pinchgames.domain.DomainEntity

class Repository(val networkDataSource: NetworkDataSource, val localDataSource: LocalDataSource) {


    fun searchGames(): GamesResult {
        Log.d("Repository", "searchGames")

        val dataSourceFactory = localDataSource.getGames()

        val boundaryCallback = PinchGamesCallback(networkDataSource, localDataSource)

        val networkErrors = boundaryCallback.networkErrors
        val loading = boundaryCallback.loading

        val pagedListConfig = createPagedConfig()

        // Build LivePagedList. Handles the load from local DB and fetch from network when no more remains in DB
        val data = LivePagedListBuilder(dataSourceFactory,pagedListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()


        return GamesResult(data, networkErrors, loading)
    }

    fun updateGames() {
        localDataSource.updateGames()
    }

    fun searchGameDetails(id: Long): LiveData<DomainEntity.Game> = localDataSource.getGameInfo(id)

    private fun createPagedConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(PAGE_SIZE)
        .setMaxSize(MAX_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()


    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_SIZE = 150
        private const val PREFETCH_DISTANCE = 40
    }
}