package by.yarik.truth_or_dare.data.main

import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto

interface IMainRepositoryCallback {

    fun onLevelsResult(list: List<LevelDomainDto>)
}