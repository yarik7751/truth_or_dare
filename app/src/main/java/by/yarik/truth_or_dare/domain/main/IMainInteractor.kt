package by.yarik.truth_or_dare.domain.main

import by.yarik.truth_or_dare.domain.IBaseInteractor

interface IMainInteractor: IBaseInteractor {

    fun initCallback(callback: IMainInteractorCallback)

    fun getLevels()
}