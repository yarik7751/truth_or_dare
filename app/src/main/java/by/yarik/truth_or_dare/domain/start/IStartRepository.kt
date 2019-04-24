package by.yarik.truth_or_dare.domain.start

import by.yarik.truth_or_dare.data.BaseRepository
import by.yarik.truth_or_dare.data.start.IStartRepositoryCallback
import by.yarik.truth_or_dare.domain.IBaseRepository
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Observable

interface IStartRepository: IBaseRepository {

    fun initCallback(callback: IStartRepositoryCallback)

    fun getVersion()

    fun getLevels()

    fun getLocalVersion()

    fun setLocalVersion(newVersion: String)

    fun insertLevelsToDb(levelsDomain: List<LevelDomainDto>): Observable<Unit>
}