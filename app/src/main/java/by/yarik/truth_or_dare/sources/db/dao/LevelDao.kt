package by.yarik.truth_or_dare.sources.db.dao

import android.arch.persistence.room.*
import by.yarik.truth_or_dare.sources.db.entity.LevelDb

@Dao
interface LevelDao {

    @Insert
    fun insert(level: LevelDb)

    @Update
    fun update(level: LevelDb)

    @Delete
    fun delete(level: LevelDb)

    @Query("SELECT * FROM levels")
    fun getAllLevels(): List<LevelDb>
}