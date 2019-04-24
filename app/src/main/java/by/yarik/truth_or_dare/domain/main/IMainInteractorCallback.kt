package by.yarik.truth_or_dare.domain.main

import by.yarik.truth_or_dare.view.main.viewmodel.LevelViewModel
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Observable

interface IMainInteractorCallback {

    fun onLevelsResult(list: Observable<List<LevelDomainDto>>)
}