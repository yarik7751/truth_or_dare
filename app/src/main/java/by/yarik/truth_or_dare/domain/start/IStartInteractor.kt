package by.yarik.truth_or_dare.domain.start

import by.yarik.truth_or_dare.domain.IBaseInteractor

interface IStartInteractor: IBaseInteractor {

    fun initCallback(callback: IStartInteractorCallback)

    fun getLevels()

    fun getVersion()

    fun getLocalVersion()
}