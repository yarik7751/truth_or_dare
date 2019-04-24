package by.yarik.truth_or_dare.domain.main

import by.yarik.truth_or_dare.data.main.IMainRepositoryCallback
import by.yarik.truth_or_dare.domain.BaseInteractor
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto

class MainInteractor(var repository: IMainRepository): BaseInteractor(), IMainInteractor, IMainRepositoryCallback {

    lateinit var callback: IMainInteractorCallback

    fun initCallback(callback: IMainInteractorCallback) {
        this.callback = callback
    }

    override fun getLevels() {
        repository.getLevels()
    }

    override fun onLevelsResult(list: List<LevelDomainDto>) {
        callback.onLevelsResult(list)
    }
}