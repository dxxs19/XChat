package com.xwei.xchat.common.abstract

import android.content.DialogInterface

/**
 * @desc
 * @author wei
 * @date  2022/1/1
 **/
interface DialogCallBack {

    /**
     * 点击事件，一般指点击确定按钮
     * @param dialog
     * @param which
     */
    fun onClick(dialog: DialogInterface?, which: Int)
}