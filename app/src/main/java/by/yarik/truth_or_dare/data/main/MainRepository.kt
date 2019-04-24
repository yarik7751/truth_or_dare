package by.yarik.truth_or_dare.data.main

import by.yarik.truth_or_dare.core.mappers.LevelMapper
import by.yarik.truth_or_dare.data.BaseRepository
import by.yarik.truth_or_dare.domain.main.IMainRepository
import by.yarik.truth_or_dare.sources.db.TruthOrDareDatabase

class MainRepository(var database: TruthOrDareDatabase): BaseRepository(), IMainRepository {

    lateinit var callback: IMainRepositoryCallback

    override fun initCallback(callback: IMainRepositoryCallback) {
        this.callback = callback
    }

    override fun getLevels() {
        val listLevelsDb = database.levelDao().getAllLevels()
        val listLevels = LevelMapper.dbToDomain(listLevelsDb)
        callback.onLevelsResult(listLevels)
    }
}