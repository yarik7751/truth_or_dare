package by.yarik.truth_or_dare.core.mappers

import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.sources.firebase.pojo.LevelResponse
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto

object LevelMapper {

    fun responseToDomain(responses: List<LevelResponse>): List<LevelDomainDto> {
        val result = mutableListOf<LevelDomainDto>()
        responses.forEach {
            val domainDto = responseToDomainItem(it)
            result.add(domainDto)
        }

        return result
    }

    private fun responseToDomainItem(response: LevelResponse): LevelDomainDto {
        val enTitle = response.title.enTitle
        val ruTitle = response.title.ruTitle
        val value = response.value

        return LevelDomainDto(value, enTitle, ruTitle)
    }

    fun domainToDb(domains: List<LevelDomainDto>): List<LevelDb> {
        val result = mutableListOf<LevelDb>()
        domains.forEach {
            val dbObject = domainToDb(it)
            result.add(dbObject)
        }

        return result
    }

    private fun domainToDb(domain: LevelDomainDto): LevelDb {
        val value = domain.value
        val enTitle = domain.enTitle
        val ruTitle = domain.ruTitle

        return LevelDb(0, enTitle, ruTitle, value)
    }

    public fun dbToDomain(dbLevels: List<LevelDb>): List<LevelDomainDto> {
        val result = mutableListOf<LevelDomainDto>()
        dbLevels.forEach {
            result.add(dbToDomainItem(it))
        }

        return result
    }

    private fun dbToDomainItem(dbLevel: LevelDb): LevelDomainDto {
        val value = dbLevel.value
        val enTitle = dbLevel.enTitle
        val ruTitle = dbLevel.ruTitle

        return LevelDomainDto(value, enTitle, ruTitle)
    }
}