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

class StartPresenter(view : IStartView, resourceManager: IResourceManager) : BasePresenter<IStartView>(view, resourceManager), IStartPresenter {

    companion object {
        const val LEVELS = "levels"
        const val VERSION = "version"
    }

    var levels: MutableList<LevelResponse>

    init {
        levels = mutableListOf()
    }

    override fun onCreateView() {
    }

    override fun onViewCreated(bundle: Bundle?) {
        getVersion()
        //getLevels()

    }

    private fun getVersion() {
        val mainReference = getMainReference()
        val versionReference = mainReference.child(VERSION)
        val preferences = Preferences.getInstance(resourceManager.getContext())

        versionReference.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("versionReference", "onDataChange")
                val version: String = dataSnapshot.value.toString()
                val currentVersion = preferences!!.getDataVersion()

                if(!version.contentEquals(currentVersion)) {
                    preferences.setDataVersion(version)
                    getLevels()
                } else {
                    view.openMainScreen()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("versionReference", "onCancelled")
            }
        })
    }

    private fun getLevels() {
        view.showLoading()
        val mainReference = getMainReference()
        val levelReference = mainReference.child(LEVELS)

        levelReference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(post in dataSnapshot.children) {
                    val level: LevelResponse = post.getValue(LevelResponse::class.java)!!
                    levels.add(level)
                }

                addLevelsIntoDb()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.showError(databaseError.message)
            }
        })
    }

    private fun getMainReference(): DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        return database.reference
    }

    @SuppressLint("CheckResult")
    private fun addLevelsIntoDb() {
        val levelDao = TruthOrDareApplication.getInstance().roomDatabase.levelDao()
        val levelsDb = LevelMapper.mappingLevelItems(levels)

        Observable.fromCallable {
            levelsDb.forEach {
                levelDao.insert(it)
            }
        }
            .doOnTerminate {
                view.hideLoading()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.openMainScreen()
            }, {
                view.showError(resourceManager.getString(R.string.room_error))
            })
    }
}