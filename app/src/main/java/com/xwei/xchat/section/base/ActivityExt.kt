package com.xwei.xchat.section.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.xwei.commonutils.util.showToast
import com.xwei.customview.dialog.CusProgressDialog
import com.xwei.xchat.R
import com.xwei.xchat.common.abstract.DialogCallBack
import com.xwei.xchat.common.abstract.OnResourceParseCallback
import com.xwei.xchat.common.abstract.UIHost
import com.xwei.xchat.common.enums.Status
import com.xwei.xchat.common.network.Resource

/**
 * @desc
 * @author wei
 * @date  2022/1/1
 **/
open class ActivityExt : AppCompatActivity(), UIHost {

    private var progressDialog: CusProgressDialog? = null

    /**
     * 解析Resource<T>
     * @param response
     * @param callback
     * @param <T>
     */
    override fun <T> parseResource(response: Resource<T>, callBack: OnResourceParseCallback<T>) {
        when (response.status) {
            Status.SUCCESS -> {
                callBack.hideLoading()
                callBack.onSuccess(response.data)
            }

            Status.LOADING -> {
                callBack.onLoading(response.data)
            }

            Status.ERROR -> {
                callBack.hideLoading()
                if (!callBack.hideErrorMsg) {
                    response.getMessage()?.let { showToast(it) }
                }
                callBack.onError(response.errorCode, response.getMessage())
            }
        }
    }

    override fun showToast(message: String) {
        message.showToast(this)
    }

    override fun showToast(messageId: Int) {
        messageId.showToast(this)
    }

    override fun showDialog(message: Int, callBack: DialogCallBack?) {
        showDialog(getString(R.string.em_dialog_default_title), getString(message), "确定",
            "取消", callBack
        )
    }

    override fun showDialog(message: String, callBack: DialogCallBack?) {
        showDialog(getString(R.string.em_dialog_default_title), message, "确定",
            "取消", callBack
        )
    }

    override fun showDialog(title: Int, message: Int, callBack: DialogCallBack?) {
        showDialog(getString(title), getString(message), "确定",
            "取消", callBack
        )
    }

    override fun showDialog(
        title: String,
        message: String,
        pBtnTxt: String,
        nBtnTxt: String,
        callBack: DialogCallBack?
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(pBtnTxt) { dialog, which ->
                callBack?.onClick(dialog, which)
            }
            .setNegativeButton(nBtnTxt, null)
            .show()
    }

    override fun showLoading() {
        showLoading(getString(R.string.loading))
    }

    override fun showLoading(message: String?) {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }

        progressDialog = message?.let {
            CusProgressDialog.Companion.Builder(this)
                .setLoadingMessage(it)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .show()
        }
    }

    override fun dismissLoading() {
        progressDialog?.let {
            it.dismiss()
            progressDialog = null
        }
    }

}