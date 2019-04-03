package by.yarik.truth_or_dare.base

import android.os.Bundle

interface IBasePresenter {

    fun onCreateView()

    fun onViewCreated(bundle: Bundle?)

    fun onDestroy() {}
}