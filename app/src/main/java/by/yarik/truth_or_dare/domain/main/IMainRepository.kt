package by.yarik.truth_or_dare.domain.main

import by.yarik.truth_or_dare.data.main.IMainRepositoryCallback
import by.yarik.truth_or_dare.domain.IBaseRepository

interface IMainRepository: IBaseRepository {

    fun initCallback(callback: IMainRepositoryCallback)

    fun getLevels()
}