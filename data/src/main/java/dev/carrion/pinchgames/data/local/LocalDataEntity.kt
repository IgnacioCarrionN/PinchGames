package dev.carrion.pinchgames.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

sealed class LocalDataEntity {
    @Entity(tableName = "games_table", indices = [Index(value = ["gameId"], unique = true)])
    data class Game(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val gameId: Long,
        val name: String,
        val slug: String,
        val url: String,
        val created_at: Long,
        val updated_at: Long,
        val summary: String?,
        val storyline: String?,
        val rating: Double?,
        val rating_count: Int?,
        val cover: String?
    ): LocalDataEntity()
}