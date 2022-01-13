package com.xwei.customview.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.xwei.customview.R

/**
 * @desc  进度对话框
 * @author wei
 * @date  2022/1/4
 **/
class CusProgressDialog @JvmOverloads constructor(
    @NonNull context: Context,
    @StyleRes themeResId: Int = 0
) : AlertDialog(context, themeResId) {

    constructor(
        context: Context, cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : this(context) {
        cstCancelable = cancelable
        cstCancelListener = cancelListener
    }

    companion object {

        var cstCancelable: Boolean = false
        var cstCancelListener: DialogInterface.OnCancelListener? = null

        public class Builder() {
            private var mContext: Context? = null
            private var message: String? = null
            private var canceledOnTouchOutside = true
            private var cancelable = cstCancelable
            private var cancelListener: DialogInterface.OnCancelListener? = cstCancelListener

            constructor(context: Context) : this() {
                mContext = context
            }

            fun setLoadingMessage(@StringRes messageID: Int): Builder {
                this.message = mContext?.getString(messageID)
                return this
            }

            fun setLoadingMessage(message: String): Builder {
                this.message = message
                return this
            }

            fun setCancelable(cancelable: Boolean): Builder {
                this.cancelable = cancelable
                return this
            }

            fun setCanceledOnTouchOutside(cancel: Boolean): Builder {
                canceledOnTouchOutside = cancel
                return this
            }

            fun setOnCancelListener(cancelListener: DialogInterface.OnCancelListener?): Builder {
                this.cancelListener = cancelListener
                return this
            }

            private fun build(): CusProgressDialog? {
                val dialog = mContext?.let { CusProgressDialog(it, R.style.Dialog_Light) }
                dialog?.let {
                    it.setCancelable(cancelable)
                    it.setCanceledOnTouchOutside(canceledOnTouchOutside)
                    it.setMessage(message)
                    it.setOnCancelListener(cancelListener)
                }
                return dialog
            }

            fun show(): CusProgressDialog? {
                val dialog = build()
                dialog?.show()
                return dialog
            }
        }
    }


}