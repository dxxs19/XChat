package com.xwei.xchat.common.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyphenate.chat.EMClient

/**
 * @desc  仓库类
 * @author wei
 * @date  2022/1/2
 **/
class BaseRepo {

    /**
     * return a new liveData
     * @param item
     * @param <T>
     * @return
     */
    fun <T> createLiveData(item: T): LiveData<T>? {
        return MutableLiveData(item)
    }

    /**
     * login before
     * @return
     */
        fun isLoggedIn(): Boolean {
        return EMClient.getInstance().isLoggedInBefore
    }

    /**
     * 获取本地标记，是否自动登录
     * @return
     */
//    fun isAutoLogin(): Boolean {
//        return IMHelper.getInstance().getAutoLogin()
//    }

    /**
     * 获取当前用户
     * @return
     */
//    fun getCurrentUser(): String {
//        return IMHelper.getInstance().getCurrentUser()
//    }

}