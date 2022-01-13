package com.xwei.xchat.common.abstract

import androidx.annotation.StringRes
import com.xwei.xchat.common.network.Resource

/**
 * @desc
 * @author wei
 * @date  2022/1/1
 **/
interface UIHost {

    fun <T> parseResource(response: Resource<T>, callBack: OnResourceParseCallback<T>)

    fun showToast(message: String)

    fun showToast(@StringRes messageId: Int)

    fun showDialog(@StringRes message: Int, callBack: DialogCallBack?)

    fun showDialog(message: String, callBack: DialogCallBack?)

    fun showDialog(@StringRes title: Int, @StringRes message: Int, callBack: DialogCallBack?)

    fun showDialog(
        title: String, message: String, pBtnTxt: String = "确定", nBtnTxt: String = "取消",
        callBack: DialogCallBack?
    )

    fun showLoading()

    fun showLoading(message: String?)

    fun dismissLoading()
}