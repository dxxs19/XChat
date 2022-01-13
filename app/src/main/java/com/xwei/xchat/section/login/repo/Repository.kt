package com.xwei.xchat.section.login.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.hyphenate.easeui.domain.EaseUser
import com.xwei.commonutils.util.LogUtil
import com.xwei.xchat.common.abstract.ResultCallBack
import com.xwei.xchat.common.manager.IMHelper
import com.xwei.xchat.common.manager.PreferenceManager
import com.xwei.xchat.common.network.ErrorCode
import com.xwei.xchat.common.network.Resource
import com.xwei.xchat.common.repositories.NetworkRequest

/**
 * @desc
 * @author wei
 * @date  2022/1/3
 **/
object Repository {

    /**
     *  用户登录
     */
    fun login(userName: String, psw: String, token: Boolean) : LiveData<Resource<EaseUser>> {
        return object : NetworkRequest<EaseUser>() {
            override fun createRequest(callback: ResultCallBack<LiveData<EaseUser>>) {
                EMClient.getInstance().login(userName, psw, object : EMCallBack {
                    override fun onSuccess() {
                        LogUtil.e("onSuccess", "success")

                        IMHelper.getInstance().init()
                        PreferenceManager.getInstance().setCurrentUserName(userName)
                        PreferenceManager.getInstance().setCurrentUserPwd(psw)
                        // get current user id
                        val currentUser = EMClient.getInstance().currentUser
                        val user = EaseUser(currentUser)
                        callback.onSuccess(MutableLiveData(user))
                    }

                    override fun onError(code: Int, error: String?) {
                        LogUtil.e("onError", "code: $code, error: $error")
                        callback.onError(code, error)
                    }

                    override fun onProgress(progress: Int, status: String?) {
                        LogUtil.e("onProgress", "progress: $progress, status: $status")
                    }
                })
            }
        }.asLiveData()
    }

    /**
     *  获取登录用户信息
     */
    fun loadAllInfoFromHX(): LiveData<Resource<Boolean>> {
        return object : NetworkRequest<Boolean>() {
            override fun createRequest(callback: ResultCallBack<LiveData<Boolean>>) {
                if (isAutoLogin()) {
                    if (isLoggedIn()) {
                        loadAllConversationsAndGroups()
                        callback.onSuccess(createLiveData(true))
                    } else {
                        callback.onError(ErrorCode.EM_NOT_LOGIN)
                    }
                } else {
                    callback.onError(ErrorCode.EM_NOT_LOGIN)
                }
            }
        }.asLiveData()
    }

    /**
     * 从本地数据库加载所有的对话及群组
     */
    private fun loadAllConversationsAndGroups() {
        // 初始化数据库
//        initDb()
//        // 从本地数据库加载所有的对话及群组
//        getChatManager().loadAllConversations()
//        getGroupManager().loadAllGroups()
    }

    /**
     * 获取本地标记，是否自动登录
     * @return
     */
    fun isAutoLogin() : Boolean {
        return IMHelper.getInstance().getAutoLogin()
    }

    /**
     *  是否成功登录
     */
    fun isLoggedIn() : Boolean {
        return EMClient.getInstance().isLoggedInBefore
    }

    /**
     *  创建一个 LiveData
     */
    fun <T> createLiveData(data: T) : LiveData<T> {
        return MutableLiveData(data)
    }
}