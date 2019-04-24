package by.yarik.truth_or_dare.data.main

import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Observable

interface IMainRepositoryCallback {

    fun onLevelsResult(list: Observable<List<LevelDomainDto>>)
}