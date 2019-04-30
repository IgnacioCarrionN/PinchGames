package dev.carrion.pinchgames.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.carrion.pinchgames.domain.DomainEntity

data class GamesResult(
    val data: LiveData<PagedList<DomainEntity.GameShort>>,
    val networkErrors: LiveData<String>,
    val loading: LiveData<Boolean>
)