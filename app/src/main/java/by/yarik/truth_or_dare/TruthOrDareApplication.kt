package by.yarik.truth_or_dare

import android.app.Application
import com.google.firebase.FirebaseApp

class TruthOrDareApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
    }
}