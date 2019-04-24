package by.yarik.truth_or_dare.domain.main

import by.yarik.truth_or_dare.core.mappers.main.MainMapper
import by.yarik.truth_or_dare.data.main.IMainRepositoryCallback
import by.yarik.truth_or_dare.domain.BaseInteractor
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainInteractor(var repository: IMainRepository): BaseInteractor(), IMainInteractor, IMainRepositoryCallback {

    init {
        repository.initCallback(this)
    }

    lateinit var callback: IMainInteractorCallback

    override fun initCallback(callback: IMainInteractorCallback) {
        this.callback = callback
    }

    override fun getLevels() {
        repository.getLevels()
    }

    override fun onLevelsResult(list: Observable<List<LevelDomainDto>>) {
        callback.onLevelsResult(list.subscribeOn(Schedulers.io()))
    }
}