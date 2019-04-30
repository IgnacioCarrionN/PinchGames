package dev.carrion.pinchgames.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface BaseDao<T> {

    fun select(id: Long): LiveData<T>

    fun selectAll(): DataSource.Factory<Int, LocalDataEntity.Game>

    @Insert(onConflict = REPLACE)
    fun insert(t: T): Long

    @Insert(onConflict = REPLACE)
    fun insert(t: List<T>)

    @Update(onConflict = REPLACE)
    fun update(t: T)

    @Update(onConflict = REPLACE)
    fun update(t: List<T>)

    @Delete
    fun delete(t: T): Int

    @Delete
    fun delete(t: List<T>)

    fun truncate()
}