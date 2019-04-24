package by.yarik.truth_or_dare.domain.main

import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto

interface IMainInteractorCallback {

    fun onLevelsResult(list: List<LevelDomainDto>)
}