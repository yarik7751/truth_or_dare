package by.yarik.truth_or_dare.base.basepresenter

import by.yarik.truth_or_dare.base.baseview.IBaseView

abstract class BasePresenter<V : IBaseView>(var view : IBaseView) :
    IBasePresenter {

}