package by.yarik.truth_or_dare.view.main

import by.yarik.truth_or_dare.base.baseview.IBaseView
import by.yarik.truth_or_dare.sources.db.entity.LevelDb

interface IMainView: IBaseView {

    fun updateLevels(levels: List<LevelDb>)

    fun showError(text: String)
}