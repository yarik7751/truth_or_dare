package by.yarik.truth_or_dare.base.basepresenter

import by.yarik.truth_or_dare.base.baseview.IBaseView
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.core.ResourceManager

abstract class BasePresenter<V : IBaseView>(var view : V, var resourceManager: IResourceManager) : IBasePresenter