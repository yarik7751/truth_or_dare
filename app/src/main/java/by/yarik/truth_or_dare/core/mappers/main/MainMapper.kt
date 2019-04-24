package by.yarik.truth_or_dare.core.mappers.main

import by.yarik.truth_or_dare.core.mappers.LevelMapper
import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.view.main.viewmodel.LevelViewModel
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto

object MainMapper {

    fun domainToViewModel(domainList: List<LevelDomainDto>): List<LevelViewModel> {
        val result = mutableListOf<LevelViewModel>()
        domainList.forEach {
            val dbObject = domainToViewModel(it)
            result.add(dbObject)
        }

        return result
    }

    private fun domainToViewModel(domain: LevelDomainDto): LevelViewModel {
        val value = domain.value

        //todo attention
        //val ruTitle = domain.ruTitle
        val enTitle = domain.enTitle

        return LevelViewModel(value, enTitle)
    }
}