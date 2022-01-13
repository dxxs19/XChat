package com.xwei.xchat.section.login.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hyphenate.easeui.domain.EaseUser
import com.xwei.xchat.R
import com.xwei.xchat.common.abstract.OnResourceParseCallback
import com.xwei.xchat.section.base.BaseActivity
import com.xwei.xchat.section.login.viewmodels.SplashViewModel
import com.xwei.xchat.section.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * @desc  闪屏页
 * @author wei
 * @date  2021/12/27
 **/
class SplashActivity : BaseActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(SplashViewModel::class.java) }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setFitSystem(false)
        setStatusBarColor(R.color.transparent)
    }

    @SuppressLint("CheckResult")
    override fun initSetup() {
        super.initSetup()
        Observable.just(1)
            .delay(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                loginSDK()
            }
    }

    private fun loginSDK() {
        viewModel.getLoginData().observe(this, { response ->
            parseResource(response, object : OnResourceParseCallback<Boolean>(false) {
                override fun onSuccess(data: Boolean?) {
                    MainActivity.startAction(mContext)
                    finish()
                }

                override fun onError(code: Int, message: String?) {
                    super.onError(code, message)
                    LoginActivity.startAction(mContext)
                    finish()
                }
            })
        })
    }
}