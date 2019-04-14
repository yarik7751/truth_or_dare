package by.yarik.truth_or_dare.sources.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import by.yarik.truth_or_dare.sources.db.dao.LevelDao
import by.yarik.truth_or_dare.sources.db.entity.LevelDb

@Database(entities = arrayOf(LevelDb::class), version = 1)
abstract class TruthOrDareDatabase: RoomDatabase() {

    abstract fun levelDao(): LevelDao
}