package com.xwei.xchat.section.login.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.xwei.xchat.common.network.Resource
import com.xwei.xchat.section.login.repo.Repository

/**
 * @desc
 * @author wei
 * @date  2022/1/5
 **/
class SplashViewModel constructor(application: Application) : AndroidViewModel(application) {

    fun getLoginData() : LiveData<Resource<Boolean>> {
        return Repository.loadAllInfoFromHX()
    }
}