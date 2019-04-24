package by.yarik.truth_or_dare.view.main

import by.yarik.truth_or_dare.base.baseview.IBaseView
import by.yarik.truth_or_dare.sources.db.entity.LevelDb
import by.yarik.truth_or_dare.view.main.viewmodel.LevelViewModel

interface IMainView: IBaseView {

    fun initUi()

    fun updateLevels(levels: List<LevelViewModel>)

    fun showError(text: String)
}