package com.xwei.xchat.common.abstract

import com.hyphenate.EMValueCallBack

/**
 * @desc
 * @author wei
 * @date  2022/1/3
 **/
abstract class ResultCallBack<T> : EMValueCallBack<T>  {
    /**
     * 针对只返回error code的情况
     * @param error
     */
    open fun onError(error: Int) {
        onError(error, null)
    }
}