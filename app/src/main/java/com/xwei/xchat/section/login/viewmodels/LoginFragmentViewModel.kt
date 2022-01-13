package com.xwei.xchat.section.login.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.xwei.xchat.common.model.LoginUser
import com.xwei.xchat.section.login.repo.Repository

/**
 * @desc
 * @author wei
 * @date  2022/1/2
 **/
class LoginFragmentViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val userLiveData by lazy { MutableLiveData<LoginUser>() }

    fun login(userName: String, password: String, b: Boolean = false) {
        userLiveData.value = LoginUser(userName, password)
    }

    val loginResultLiveData = Transformations.switchMap(userLiveData) { user ->
        Repository.login(user.userName, user.password, false)
    }

}