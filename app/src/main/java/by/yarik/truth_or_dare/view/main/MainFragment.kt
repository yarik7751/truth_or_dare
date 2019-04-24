package by.yarik.truth_or_dare.view.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.TruthOrDareApplication
import by.yarik.truth_or_dare.base.baseview.BaseFragment
import by.yarik.truth_or_dare.data.main.MainRepository
import by.yarik.truth_or_dare.domain.main.MainInteractor
import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.view.main.viewmodel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_main.*

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
        return MainPresenter(this, resourceManager, interactor)
    }

    override fun initUi() {

    }

    override fun updateLevels(levels: List<LevelViewModel>) {
        levels.forEach {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.topMargin = resources.getDimensionPixelSize(R.dimen.margin_8)

            val levelButton = Button(context)
            levelButton.setText(it.title)
            levelButton.layoutParams = params

            levelsContainer.addView(levelButton)
        }
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}