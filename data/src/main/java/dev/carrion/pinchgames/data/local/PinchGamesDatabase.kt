package dev.carrion.pinchgames.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.carrion.pinchgames.data.local.game.GamesDao

@Database(entities = [LocalDataEntity.Game::class], version = 3, exportSchema = false)
abstract class PinchGamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao
}