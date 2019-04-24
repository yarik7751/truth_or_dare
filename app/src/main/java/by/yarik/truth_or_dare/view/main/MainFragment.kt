package by.yarik.truth_or_dare.view.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.TruthOrDareApplication
import by.yarik.truth_or_dare.base.baseview.BaseFragment
import by.yarik.truth_or_dare.data.main.MainRepository
import by.yarik.truth_or_dare.domain.main.MainInteractor
import by.yarik.truth_or_dare.sources.db.entity.LevelDb

class MainFragment: BaseFragment<IMainPresenter>(), IMainView {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment();
        }
    }

    override fun resourceLayout(): Int {
        return R.layout.fragment_main
    }

    override fun onCreateView(v: View, savedInstanceState: Bundle?): View? {
        return v
    }

    override fun initPresenter(): IMainPresenter {
        val repository = MainRepository(TruthOrDareApplication.getInstance().roomDatabase)
        val interactor = MainInteractor(repository)
        return MainPresenter(this, resourceManager, interactor, repository)
    }

    override fun updateLevels(levels: List<LevelDb>) {
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}