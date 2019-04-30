package dev.carrion.pinchgames.ui.gamedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.carrion.pinchgames.data.Repository
import dev.carrion.pinchgames.domain.DomainEntity

class GameDetailsViewModel(repository: Repository, gameId: Long) : ViewModel() {

    val gameDetail: LiveData<DomainEntity.Game> = repository.searchGameDetails(gameId)

}