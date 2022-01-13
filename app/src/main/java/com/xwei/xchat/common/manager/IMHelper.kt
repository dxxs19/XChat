package com.xwei.xchat.common.manager

import android.annotation.SuppressLint
import com.hyphenate.chat.*
import com.hyphenate.chat.EMConversation.EMConversationType
import com.hyphenate.easecallkit.EaseCallKit
import com.hyphenate.easecallkit.base.EaseCallKitConfig
import com.hyphenate.push.EMPushConfig
import com.hyphenate.push.EMPushHelper
import com.hyphenate.push.EMPushType
import com.hyphenate.push.PushListener
import com.hyphenate.util.EMLog
import com.xwei.commonutils.util.LogUtil
import com.xwei.xchat.AVApplication
import com.xwei.xchat.common.constant.KeyCenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @desc  负责sdk初始化及基本信息获取等等
 * @author wei
 * @date  2022/1/2
 **/
class IMHelper {

    private val TAG = javaClass.simpleName

    companion object {
        fun getInstance(): IMHelper {
            return IMHelperHolder.instance
        }

        private object IMHelperHolder {
            val instance = IMHelper()
        }
    }

    fun init() {
        //初始化推送
        initPush()
        //注册call Receiver
        //initReceiver(context);
        //初始化ease ui相关
        initEaseUI()
        //注册对话类型
        registerConversationType()

        //callKit初始化
        initCallKit()
    }

    @SuppressLint("CheckResult")
    private fun initCallKit() {
        val callKitConfig = EaseCallKitConfig()
        callKitConfig.callTimeOut = 30 * 1000
        callKitConfig.agoraAppId = KeyCenter.agoraAppID
        callKitConfig.isEnableRTCToken = true
        Observable.just(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                EaseCallKit.getInstance().init(AVApplication.getInstance(), callKitConfig)
            }
    }

    private fun registerConversationType() {

    }

    private fun initEaseUI() {

    }

    private fun initPush() {
        EMPushHelper.getInstance().setPushListener(object : PushListener() {
            override fun onError(pushType: EMPushType, errorCode: Long) {
                // TODO: 返回的errorCode仅9xx为环信内部错误，可从EMError中查询，其他错误请根据pushType去相应第三方推送网站查询。
                EMLog.e("PushClient", "Push client occur a error: $pushType - $errorCode")
            }

            override fun isSupportPush(pushType: EMPushType, pushConfig: EMPushConfig): Boolean {
                // 由外部实现代码判断设备是否支持FCM推送
                return super.isSupportPush(pushType, pushConfig)
            }
        })
    }

    /**
     * 判断是否之前登录过
     * @return
     */
    fun isLoggedIn(): Boolean {
        return getEMClient().isLoggedInBefore
    }

    /**
     * 获取IM SDK的入口类
     * @return
     */
    fun getEMClient(): EMClient {
        return EMClient.getInstance()
    }

    /**
     * 获取contact manager
     * @return
     */
    fun getContactManager(): EMContactManager? {
        return getEMClient().contactManager()
    }

    /**
     * 获取group manager
     * @return
     */
    fun getGroupManager(): EMGroupManager? {
        return getEMClient().groupManager()
    }

    /**
     * 获取chatroom manager
     * @return
     */
    fun getChatroomManager(): EMChatRoomManager? {
        return getEMClient().chatroomManager()
    }


    /**
     * get EMChatManager
     * @return
     */
    fun getChatManager(): EMChatManager? {
        return getEMClient().chatManager()
    }

    /**
     * get push manager
     * @return
     */
    fun getPushManager(): EMPushManager? {
        return getEMClient().pushManager()
    }

    /**
     * get conversation
     * @param username
     * @param type
     * @param createIfNotExists
     * @return
     */
    fun getConversation(
        username: String?,
        type: EMConversationType?,
        createIfNotExists: Boolean
    ): EMConversation? {
        return getChatManager()?.getConversation(username, type, createIfNotExists)
    }

    /**
     *  获取当前用户信息
     */
    fun getCurrentUser(): String? {
        return getEMClient().currentUser
    }

    /**
     * 退出登录后，需要处理的业务逻辑
     */
    fun logoutSuccess() {
        LogUtil.d(TAG, "logout: onSuccess")
        setAutoLogin(false)
    }

    /**
     * 设置本地标记，是否自动登录
     * @param autoLogin
     */
    fun setAutoLogin(autoLogin: Boolean) {
        PreferenceManager.getInstance().setAutoLogin(autoLogin)
    }

    /**
     * 获取本地标记，是否自动登录
     * @return
     */
    fun getAutoLogin(): Boolean {
        return PreferenceManager.getInstance().getAutoLogin()
    }

    /**
     * 保存当前用户密码
     * 此处保存密码是为了查看多端设备登录是，调用接口不再输入用户名及密码，实际开发中，不可在本地保存密码！
     * 注：实际开发中不可进行此操作！！！
     * @param pwd
     */
    fun setCurrentUserPwd(pwd: String?) {
        PreferenceManager.getInstance().setCurrentUserPwd(pwd)
    }

    fun getCurrentUserPwd(): String? {
        return PreferenceManager.getInstance().getCurrentUserPwd()
    }
}