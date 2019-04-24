package by.yarik.truth_or_dare.view.start

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.Preference
import android.text.TextUtils
import android.util.Log
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.TruthOrDareApplication
import by.yarik.truth_or_dare.base.basepresenter.BasePresenter
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.core.mappers.LevelMapper
import by.yarik.truth_or_dare.domain.start.IStartInteractor
import by.yarik.truth_or_dare.domain.start.IStartInteractorCallback
import by.yarik.truth_or_dare.domain.start.IStartRepository
import by.yarik.truth_or_dare.sources.db.dao.LevelDao
import by.yarik.truth_or_dare.sources.firebase.pojo.LevelResponse
import by.yarik.truth_or_dare.sources.preferences.Preferences
import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*

class StartPresenter(
    view : IStartView,
    resourceManager: IResourceManager,
    var interactor: IStartInteractor): BasePresenter<IStartView>(view, resourceManager), IStartPresenter, IStartInteractorCallback {

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
            }))
    }

    override fun rightVersion() {
        view.openMainScreen()
    }
}