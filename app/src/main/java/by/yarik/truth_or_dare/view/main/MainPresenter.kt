package by.yarik.truth_or_dare.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.TruthOrDareApplication
import by.yarik.truth_or_dare.base.basepresenter.BasePresenter
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.core.mappers.main.MainMapper
import by.yarik.truth_or_dare.domain.main.IMainInteractor
import by.yarik.truth_or_dare.domain.main.IMainInteractorCallback
import by.yarik.truth_or_dare.domain.main.IMainRepository
import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.view.main.viewmodel.LevelViewModel
import by.yarik.truth_or_dare.view.start.IStartView
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainPresenter(
    view : IMainView,
    resourceManager: IResourceManager,
    var interactor: IMainInteractor): BasePresenter<IMainView>(view, resourceManager), IMainPresenter, IMainInteractorCallback {

    init {
        interactor.initCallback(this)
    }

    override fun onCreateView() {
        view.initUi()
    }

    override fun onViewCreated(bundle: Bundle?) {
        interactor.getLevels()
    }

    override fun onLevelsResult(list: Observable<List<LevelDomainDto>>) {
        addDicposable(list.map {
            return@map MainMapper.domainToViewModel(it)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.updateLevels(it)
            }, {
                onThrowable(it)
            }))
    }
}