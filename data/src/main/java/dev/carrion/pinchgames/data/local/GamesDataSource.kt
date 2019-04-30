package dev.carrion.pinchgames.data.local

import androidx.arch.core.util.Function
import androidx.paging.DataSource
import dev.carrion.pinchgames.domain.DomainEntity

class GamesDataSource : DataSource.Factory<Int, DomainEntity.GameShort>() {
    override fun <ToValue : Any?> map(function: Function<DomainEntity.GameShort, ToValue>): DataSource.Factory<Int, ToValue> {
        return super.map(function)
    }

    override fun create(): DataSource<Int, DomainEntity.GameShort> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <ToValue : Any?> mapByPage(function: Function<MutableList<DomainEntity.GameShort>, MutableList<ToValue>>): DataSource.Factory<Int, ToValue> {
        return super.mapByPage(function)
    }
}