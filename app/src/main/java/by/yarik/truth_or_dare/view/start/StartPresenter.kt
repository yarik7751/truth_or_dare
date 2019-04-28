package by.yarik.truth_or_dare.view.start

import android.os.Bundle
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.base.basepresenter.BasePresenter
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.domain.start.IStartInteractor
import by.yarik.truth_or_dare.domain.start.IStartInteractorCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class StartPresenter(
    view: IStartView,
    resourceManager: IResourceManager,
    var interactor: IStartInteractor
) : BasePresenter<IStartView>(view, resourceManager), IStartPresenter, IStartInteractorCallback {

    init {
        interactor.initCallback(this)
    }

    override fun onCreateView() {
    }

    override fun onViewCreated(bundle: Bundle?) {
        getLevels()
    }

    private fun getLevels() {
        interactor.getLevels()
    }

    override fun onLevelsError(error: String) {
        view.showError(resourceManager.getString(R.string.server_error))
    }

    override fun onVersionError(error: String) {
        view.showError(resourceManager.getString(R.string.server_error))
    }

    override fun insertLevelsToDb(insertLevelsDb: Observable<Unit>) {
        addDicposable(insertLevelsDb
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.openMainScreen()
            }, {
                onThrowable(it)
            })
        )
    }

    override fun rightVersion() {
        view.openMainScreen()
    }
}