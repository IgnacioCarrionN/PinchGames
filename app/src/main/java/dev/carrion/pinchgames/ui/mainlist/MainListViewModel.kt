package dev.carrion.pinchgames.ui.mainlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dev.carrion.pinchgames.data.GamesResult
import dev.carrion.pinchgames.data.Repository
import dev.carrion.pinchgames.domain.DomainEntity


class MainListViewModel(private val repository: Repository) : ViewModel() {

    private val gamesResult: GamesResult = repository.searchGames()

    val networkErrors: LiveData<String> = gamesResult.networkErrors

    val loading: LiveData<Boolean> = gamesResult.loading

    val gameList: LiveData<PagedList<DomainEntity.GameShort>> = gamesResult.data

    fun updateData(){
        repository.updateGames()
    }

}