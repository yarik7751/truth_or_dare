package by.yarik.truth_or_dare.domain.start

import by.yarik.truth_or_dare.core.mappers.LevelMapper
import by.yarik.truth_or_dare.data.start.IStartRepositoryCallback
import by.yarik.truth_or_dare.domain.BaseInteractor
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class StartInteractor(var repository: IStartRepository): BaseInteractor(), IStartInteractor, IStartRepositoryCallback {

    lateinit var version: String
    lateinit var levels: List<LevelDomainDto>

    lateinit var callback: IStartInteractorCallback

    init {
        repository.initCallback(this)
    }

    override fun initCallback(callback: IStartInteractorCallback) {
        this.callback = callback
    }

    override fun getLevels() {
        repository.getLevels()
    }

    override fun onLevelsResult(list: List<LevelDomainDto>) {
        levels = list
        getVersion()
    }

    override fun onLevelsError(error: String) {
        callback.onLevelsError(error)
    }

    override fun getVersion() {
        repository.getVersion()
    }

    override fun onVersionResult(version: String) {
        this.version = version
        getLocalVersion()
    }

    override fun onVersionError(error: String) {
        callback.onVersionError(error)
    }

    override fun getLocalVersion() {
        repository.getLocalVersion()
    }

    override fun onLocalVersionResult(localVersion: String) {
        if(localVersion.contentEquals(version)) {
            callback.insertLevelsToDb(repository.insertLevelsToDb(levels).subscribeOn(Schedulers.io()))
        } else {
            callback.rightVersion()
        }
    }
}