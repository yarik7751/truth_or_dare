package by.yarik.truth_or_dare.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.TruthOrDareApplication
import by.yarik.truth_or_dare.base.basepresenter.BasePresenter
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.view.start.IStartView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainPresenter(view : IMainView, resourceManager: IResourceManager): BasePresenter<IMainView>(view, resourceManager), IMainPresenter {

    lateinit var levels: List<LevelDb>

    override fun onCreateView() {
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(bundle: Bundle?) {
        Observable.fromCallable {
            val levelsDao = TruthOrDareApplication.getInstance().roomDatabase.levelDao()
            levels = levelsDao.getAllLevels()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.updateLevels(levels)
            }, {
                view.showError(resourceManager.getString(R.string.room_error))
            })
    }
}