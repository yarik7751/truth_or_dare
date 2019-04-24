package by.yarik.truth_or_dare.domain.start

import io.reactivex.Observable

interface IStartInteractorCallback {

    fun onLevelsError(error: String)

    fun onVersionError(error: String)

    fun insertLevelsToDb(insertLevelsDb: Observable<Unit>);

    fun rightVersion();
}