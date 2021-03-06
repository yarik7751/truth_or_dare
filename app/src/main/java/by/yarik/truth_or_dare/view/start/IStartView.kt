package by.yarik.truth_or_dare.view.start

import by.yarik.truth_or_dare.base.baseview.IBaseView

interface IStartView : IBaseView {

    fun openMainScreen()

    fun showError(text: String)
}