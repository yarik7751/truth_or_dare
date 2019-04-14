package by.yarik.truth_or_dare

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import by.yarik.truth_or_dare.sources.db.TruthOrDareDatabase
import com.google.firebase.FirebaseApp

class TruthOrDareApplication: Application() {

    lateinit var roomDatabase: TruthOrDareDatabase

    companion object {
        const val DB_NAME = "truth_or_dare_db";

        lateinit var app: TruthOrDareApplication

        fun getInstance(): TruthOrDareApplication {
            return app;
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        FirebaseApp.initializeApp(applicationContext)
        roomDatabase = Room.databaseBuilder(this, TruthOrDareDatabase::class.java, DB_NAME).build()
    }


}