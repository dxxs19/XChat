package com.xwei.xchat.section.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyphenate.easeui.ui.base.EaseBaseFragment
import com.xwei.xchat.common.abstract.DialogCallBack
import com.xwei.xchat.common.abstract.OnResourceParseCallback
import com.xwei.xchat.common.abstract.UIHost
import com.xwei.xchat.common.network.Resource

/**
 * @desc
 * @author wei
 * @date  2022/1/1
 **/
 abstract class BaseFragment : EaseBaseFragment(), UIHost {
    var mBAContext: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mBAContext = context as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        initArgument()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initViewModel()
        initListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    open fun initData() {

    }

    open fun initArgument() {

    }

    abstract fun getLayoutId(): Int


    open fun initListener() {

    }

    open fun initViewModel() {

    }

    open fun initView(savedInstanceState: Bundle?) {

    }

    override fun <T> parseResource(response: Resource<T>, callBack: OnResourceParseCallback<T>) {
        mBAContext?.parseResource(response, callBack)
    }

    override fun showToast(message: String) {
        mBAContext?.showToast(message)
    }

    override fun showToast(messageId: Int) {
        mBAContext?.showToast(messageId)
    }

    override fun showLoading() {
        mBAContext?.showLoading()
    }

    override fun showLoading(message: String?) {
        mBAContext?.showLoading(message)
    }

    override fun dismissLoading() {
        mBAContext?.dismissLoading()
    }

    override fun showDialog(message: Int, callBack: DialogCallBack?) {
        mBAContext?.showDialog(message, callBack)
    }

    override fun showDialog(message: String, callBack: DialogCallBack?) {
        mBAContext?.showDialog(message, callBack)
    }

    override fun showDialog(title: Int, message: Int, callBack: DialogCallBack?) {
        mBAContext?.showDialog(title, message, callBack)
    }

    override fun showDialog(
        title: String,
        message: String,
        pBtnTxt: String,
        nBtnTxt: String,
        callBack: DialogCallBack?
    ) {
        mBAContext?.showDialog(title, message, pBtnTxt, nBtnTxt, callBack)
    }
}