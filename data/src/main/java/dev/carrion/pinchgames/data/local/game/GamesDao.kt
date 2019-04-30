package dev.carrion.pinchgames.data.local.game

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.carrion.pinchgames.data.local.BaseDao
import dev.carrion.pinchgames.data.local.LocalDataEntity

@Dao
interface GamesDao : BaseDao<LocalDataEntity.Game> {

    @Query("SELECT * FROM games_table WHERE id = :id")
    override fun select(id: Long): LiveData<LocalDataEntity.Game>

    @Query("SELECT * FROM games_table ORDER BY id")
    override fun selectAll(): DataSource.Factory<Int, LocalDataEntity.Game>

    @Query("DELETE FROM games_table")
    override fun truncate()

    @Transaction
    fun deleteTableAndInsertData(newList: List<LocalDataEntity.Game>){
        truncate()
        insert(newList)
    }
}