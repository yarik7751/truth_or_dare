package by.yarik.truth_or_dare.base.basepresenter

import by.yarik.truth_or_dare.base.baseview.IBaseView
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.core.ResourceManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : IBaseView>(var view : V, var resourceManager: IResourceManager) : IBasePresenter {
    lateinit var compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable();
    }

    protected fun addDicposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    protected fun onThrowable(throwable: Throwable) {

    }
}