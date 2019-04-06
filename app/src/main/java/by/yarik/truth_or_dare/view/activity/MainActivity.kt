package by.yarik.truth_or_dare.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.view.activity.base.BaseActivity
import by.yarik.truth_or_dare.view.start.StartFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            setMainFragment(StartFragment.newInstance())
        }
    }
}
