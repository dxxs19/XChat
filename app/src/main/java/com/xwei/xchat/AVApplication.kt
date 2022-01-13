package com.xwei.xchat

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.util.EMLog
import com.xwei.commonutils.util.LogUtil
import com.xwei.commonutils.util.OSUtil
import com.xwei.xchat.common.manager.IMHelper
import com.xwei.xchat.common.manager.PreferenceManager


class AVApplication : Application(), Thread.UncaughtExceptionHandler {
    private val TAG = javaClass.simpleName

    companion object {
        lateinit var appContext: AVApplication

        fun getInstance(): AVApplication {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this;
        initOptions()
        initHx()
    }

    private fun initHx() {
        // 初始化PreferenceManager
        PreferenceManager.init(this)
        // init hx sdk
        // init hx sdk
        if (IMHelper.getInstance().getAutoLogin()) {
            EMLog.i("DemoApplication", "application initHx")
            IMHelper.getInstance().init()
        }
    }

    private fun initOptions() {
        val options = EMOptions()
        // 默认添加好友时，是不需要验证的，改成true需要验证
        options.acceptInvitationAlways = false
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，
        // 需要开发者自己处理附件消息的上传和下载
        options.autoTransferMessageAttachments = true
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true)

        val pid = OSUtil.getPid()
        val processAppName: String = OSUtil.getAppName(appContext, pid)
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if ( !OSUtil.isAppProcess(appContext, processAppName)) {
            LogUtil.e(TAG, "enter the service process!")
            // 则此application::onCreate 是被service 调用的，直接返回
            return
        }
        //初始化
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        LogUtil.e(TAG, e?.message ?: "")
    }


}