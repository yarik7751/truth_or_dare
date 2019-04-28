package by.yarik.truth_or_dare.view.start

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.TruthOrDareApplication
import by.yarik.truth_or_dare.base.baseview.BaseFragment
import by.yarik.truth_or_dare.data.start.StartRepository
import by.yarik.truth_or_dare.domain.start.StartInteractor
import by.yarik.truth_or_dare.sources.preferences.Preferences
import com.google.firebase.database.FirebaseDatabase

class StartFragment : BaseFragment<IStartPresenter>(), IStartView {

    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }

    override fun resourceLayout(): Int {
        return R.layout.fragment_start
    }

    override fun onCreateView(v: View, savedInstanceState: Bundle?): View? {
        return v
    }

    override fun initPresenter(): IStartPresenter {
        val repository = StartRepository(
            FirebaseDatabase.getInstance(),
            Preferences.getInstance(context)!!,
            TruthOrDareApplication.getInstance().roomDatabase
        )
        val interactor = StartInteractor(repository)
        return StartPresenter(this, resourceManager, interactor)
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun openMainScreen() {
        navigation.openMainScreen()
    }
}