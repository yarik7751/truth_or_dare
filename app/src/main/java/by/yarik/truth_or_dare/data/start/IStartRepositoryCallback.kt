package by.yarik.truth_or_dare.data.start

import by.yarik.truth_or_dare.domain.start.IStartInteractorCallback
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto

interface IStartRepositoryCallback {

    fun onLevelsResult(list: List<LevelDomainDto>)

    fun onLevelsError(error: String)

    fun onVersionResult(version: String)

    fun onVersionError(error: String)

    fun onLocalVersionResult(version: String)
}