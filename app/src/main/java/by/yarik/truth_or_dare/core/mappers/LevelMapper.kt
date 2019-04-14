package by.yarik.truth_or_dare.core.mappers

import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.sources.firebase.pojo.LevelResponse

object LevelMapper {

    fun mappingLevelItems(responses: List<LevelResponse>): List<LevelDb> {
        val result = mutableListOf<LevelDb>()
        responses.forEach(){
            result.add(applyLevelMapper(it))
        }

        return result
    }

    fun applyLevelMapper(response: LevelResponse): LevelDb {
        val enTitle = response.title.enTitle
        val ruTitle = response.title.ruTitle
        val value = response.value

        return LevelDb(0, enTitle, ruTitle, value)
    }
}