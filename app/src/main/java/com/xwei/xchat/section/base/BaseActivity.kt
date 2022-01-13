package com.xwei.xchat.section.base

import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.xwei.commonutils.ui.FitScreen
import com.xwei.commonutils.ui.StatusBarCompat

abstract class BaseActivity : ActivityExt() {

    protected var TAG = javaClass.simpleName
    protected lateinit var mContext: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFormat(PixelFormat.TRANSPARENT)

        super.onCreate(savedInstanceState)
        FitScreen.setCustomDensity(application, this)
        setContentView(getLayoutResId())
        initializeFlow(savedInstanceState)
    }

    /**
     *  获取布局文件id
     */
    abstract fun getLayoutResId(): Int

    /**
     *  统一流程
     */
    private fun initializeFlow(savedInstanceState: Bundle?) {
        initData()
        initView(savedInstanceState)
        initNavBar()
        initSetup()
    }

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     *  初始化View
     */
    open fun initView(savedInstanceState: Bundle?) {}

    /**
     * 初始化navBar
     */
    open fun initNavBar() {}

    /**
     * 设置事件或监听等
     */
    open fun initSetup() {}

    /**
     *  设置状态栏颜色
     */
    protected fun setStatusBarColor(colorId: Int) {
        StatusBarCompat.compat(this, ContextCompat.getColor(mContext, colorId))
    }

    /**
     * 设置是否是沉浸式
     * @param fitSystemForTheme
     */
    protected fun setFitSystem(fitSystemForTheme: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        if (fitSystemForTheme) {
            val contentFrameLayout = findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
            val parentView = contentFrameLayout.getChildAt(0)
            if (parentView != null && Build.VERSION.SDK_INT >= 14) {
                parentView.fitsSystemWindows = true
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
    }

}