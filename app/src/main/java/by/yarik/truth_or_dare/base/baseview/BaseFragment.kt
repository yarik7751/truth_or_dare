package by.yarik.truth_or_dare.base.baseview

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.yarik.truth_or_dare.R
import by.yarik.truth_or_dare.base.basepresenter.IBasePresenter
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.core.ResourceManager

abstract class BaseFragment<P : IBasePresenter>() : Fragment(), IBaseView {

    lateinit var presenter: IBasePresenter

    private lateinit var progressDialog: ProgressDialog
    protected lateinit var resourceManager: IResourceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resourceManager = ResourceManager(context);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(resourceLayout(), container, false);

        initProgressDialog()

        presenter = initPresenter();
        presenter.onCreateView()

        return onCreateView(view, savedInstanceState)
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(context);
        progressDialog.setCancelable(false)
        progressDialog.setMessage(getString(R.string.please_wait))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    abstract fun resourceLayout(): Int

    abstract fun onCreateView(v: View, savedInstanceState: Bundle?): View?

    abstract fun initPresenter(): P
}