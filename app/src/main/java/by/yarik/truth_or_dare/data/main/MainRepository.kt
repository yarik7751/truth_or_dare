package by.yarik.truth_or_dare.data.main

import by.yarik.truth_or_dare.core.mappers.LevelMapper
import by.yarik.truth_or_dare.data.BaseRepository
import by.yarik.truth_or_dare.domain.main.IMainRepository
import by.yarik.truth_or_dare.sources.db.TruthOrDareDatabase
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Observable

class MainRepository(var database: TruthOrDareDatabase): BaseRepository(), IMainRepository {

    lateinit var callback: IMainRepositoryCallback

    override fun initCallback(callback: IMainRepositoryCallback) {
        this.callback = callback
    }

    override fun getLevels() {
        callback.onLevelsResult(Observable.fromCallable {
            return@fromCallable getLevelsFromDb()
        })
    }

    private fun getLevelsFromDb(): List<LevelDomainDto> {
        val listLevelsDb = database.levelDao().getAllLevels()
        return LevelMapper.dbToDomain(listLevelsDb)
    }
}