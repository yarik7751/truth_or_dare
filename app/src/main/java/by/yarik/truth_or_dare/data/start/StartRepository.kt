package by.yarik.truth_or_dare.data.start

import by.yarik.truth_or_dare.core.mappers.LevelMapper
import by.yarik.truth_or_dare.data.BaseRepository
import by.yarik.truth_or_dare.domain.start.IStartRepository
import by.yarik.truth_or_dare.sources.db.TruthOrDareDatabase
import by.yarik.truth_or_dare.sources.firebase.pojo.LevelResponse
import by.yarik.truth_or_dare.sources.preferences.Preferences
import by.yarik.truth_or_dare.view.start.domaindto.LevelDomainDto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable

class StartRepository(
    var firebaseDatabase: FirebaseDatabase,
    var preferences: Preferences,
    var database: TruthOrDareDatabase
) : BaseRepository(), IStartRepository {

    companion object {
        const val LEVELS = "levels"
        const val VERSION = "version"
    }

    lateinit var callback: IStartRepositoryCallback

    override fun initCallback(callback: IStartRepositoryCallback) {
        this.callback = callback
    }

    override fun getVersion() {
        val mainReference = firebaseDatabase.reference
        val versionReference = mainReference.child(VERSION)

        versionReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val version: String = dataSnapshot.value.toString()

                callback.onVersionResult(version)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onVersionError(databaseError.message)
            }
        })
    }

    override fun getLevels() {
        val mainReference = firebaseDatabase.reference
        val levelReference = mainReference.child(LEVELS)

        levelReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val levels: MutableList<LevelResponse> = mutableListOf()

                for (post in dataSnapshot.children) {
                    val level: LevelResponse = post.getValue(LevelResponse::class.java)!!
                    levels.add(level)
                }

                callback.onLevelsResult(LevelMapper.responseToDomain(levels))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onLevelsError(databaseError.message)
            }
        })
    }

    override fun getLocalVersion() {
        callback.onLocalVersionResult(preferences.getDataVersion())
    }

    override fun setLocalVersion(newVersion: String) {
        preferences.setDataVersion(newVersion)
    }

    override fun insertLevelsToDb(levelsDomain: List<LevelDomainDto>): Observable<Unit> {
        val levelDao = database.levelDao()
        val levelsDb = LevelMapper.domainToDb(levelsDomain)

        return Observable.fromCallable {
            levelsDb.forEach {
                levelDao.insert(it)
            }
        }

    }
}