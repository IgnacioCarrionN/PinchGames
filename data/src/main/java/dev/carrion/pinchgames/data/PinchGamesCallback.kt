package dev.carrion.pinchgames.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import dev.carrion.pinchgames.data.local.LocalDataSource
import dev.carrion.pinchgames.data.mappers.toLocalData
import dev.carrion.pinchgames.data.network.NetworkDataSource
import dev.carrion.pinchgames.domain.DomainEntity

class PinchGamesCallback(private val networkDataSource: NetworkDataSource, private val localDataSource: LocalDataSource)
    : PagedList.BoundaryCallback<DomainEntity.GameShort>() {

    private var lastPageRequested = 0

    private var isRequestInProgress = false

    val loading = MutableLiveData<Boolean>()

    val networkErrors = MutableLiveData<String>()

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        lastPageRequested = 0
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: DomainEntity.GameShort) {
        super.onItemAtEndLoaded(itemAtEnd)
        requestAndSaveData()
    }


    private fun requestAndSaveData() {
        if(isRequestInProgress) return

        loading.postValue(true)
        isRequestInProgress = true
        val startPosition = lastPageRequested * NetworkDataSource.NETWORK_PAGE_SIZE

        networkDataSource.getGames(NetworkDataSource.NETWORK_PAGE_SIZE, startPosition, { networkDataList ->
            // Map list to DatabaseEntity.Game
            val dataBaseGames = networkDataList.toLocalData()
            localDataSource.insertGames(dataBaseGames) {
                lastPageRequested++
                isRequestInProgress = false
                loading.postValue(false)
            }
        },{ error ->
            isRequestInProgress = false
            loading.postValue(false)
            networkErrors.postValue(error)
        })
    }


}