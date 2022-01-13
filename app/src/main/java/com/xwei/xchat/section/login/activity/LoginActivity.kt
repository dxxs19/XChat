package com.xwei.xchat.section.login.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xwei.xchat.R
import com.xwei.xchat.section.base.BaseActivity
import com.xwei.xchat.section.login.fragment.LoginFragment

/**
 * @desc  登录界面
 * @author wei
 * @date  2021/12/31
 **/
class LoginActivity : BaseActivity() {

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setFitSystem(false)
        setStatusBarColor(R.color.transparent)
        supportFragmentManager.beginTransaction().replace(R.id.fl_fragment, LoginFragment()).commitNow()
    }

}