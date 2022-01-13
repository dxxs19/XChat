package com.xwei.xchat.section.login.fragment

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.hyphenate.EMError
import com.hyphenate.easeui.domain.EaseUser
import com.xwei.commonutils.util.LogUtil
import com.xwei.xchat.R
import com.xwei.xchat.common.abstract.OnResourceParseCallback
import com.xwei.xchat.common.manager.IMHelper
import com.xwei.xchat.section.base.BaseFragment
import com.xwei.xchat.section.login.viewmodels.LoginFragmentViewModel
import com.xwei.xchat.section.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_me.*

/**
 * @desc  登录 fragment
 * @author wei
 * @date  2022/1/1
 **/
class LoginFragment : BaseFragment(), View.OnClickListener, TextWatcher,
    TextView.OnEditorActionListener {

    private var userName = ""
    private var password = ""

    private val viewModel by lazy { ViewModelProvider(this).get(LoginFragmentViewModel::class.java) }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initListener() {
        super.initListener()
        btn_login.setOnClickListener(this)
        tv_login_register.setOnClickListener(this)
        tv_login_server_set.setOnClickListener(this)
        et_login_name.addTextChangedListener(this)
        et_login_pwd.addTextChangedListener(this)
        et_login_pwd.setOnEditorActionListener(this)
    }

    override fun initData() {
        super.initData()
        if (!IMHelper.getInstance().getAutoLogin()) {
            et_login_name.setText(IMHelper.getInstance().getCurrentUser())
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loginResultLiveData.observe(this, { result ->
            LogUtil.e(javaClass.simpleName, "result : $result")
            parseResource(result, object : OnResourceParseCallback<EaseUser>(false) {
                override fun onSuccess(data: EaseUser?) {
                    LogUtil.e("", "data: ${data.toString()}")
                    IMHelper.getInstance().setAutoLogin(true)
                    mBAContext?.let {
                        MainActivity.startAction(it)
                        it.finish()
                    }
                }

                override fun onError(code: Int, message: String?) {
                    super.onError(code, message)
                    if (code == EMError.USER_AUTHENTICATION_FAILED) {
                        showToast(R.string.demo_error_user_authentication_failed)
                    } else {
                        message?.let { showToast(it) }
                    }

                }

                override fun onLoading(data: EaseUser?) {
                    super.onLoading(data)
                    showLoading()
                }

                override fun hideLoading() {
                    super.hideLoading()
                    dismissLoading()
                }
            })
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_login -> {
                hideKeyboard()
                loginToServer()
            }

            R.id.tv_login_register -> {

            }

            R.id.tv_login_server_set -> {

            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
    override fun afterTextChanged(editable: Editable?) {
        userName = et_login_name.text.toString().trim()
        password = et_login_pwd.text.toString().trim()
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                hideKeyboard()
                loginToServer()
                return true
            }
        }
        return false
    }

    private fun loginToServer() {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mBAContext?.showToast(R.string.em_login_btn_info_incomplete)
            return
        }
        viewModel.login(userName, password, false)
    }
}