package by.yarik.truth_or_dare.view.activity.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import by.yarik.truth_or_dare.R
import kotlinx.android.synthetic.main.activity_main.view.*

open class BaseActivity : AppCompatActivity() {

    public fun setMainFragment(fragment : Fragment) {
        setFragment(fragment, false)
    }

    public fun setCurrentFragment(fragment : Fragment) {
        setFragment(fragment, true)
    }

    public fun setFragment(fragment : Fragment, add : Boolean) {
        var fragmentManager = supportFragmentManager;
        var fragmentTransaction = fragmentManager.beginTransaction();

        val fragmentName = fragment.javaClass.name;

        fragmentTransaction.replace(R.id.container, fragment, fragmentName)

        if(add) {
            fragmentTransaction.addToBackStack(fragmentName)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }
}