package com.xwei.xchat.common.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.xwei.commonutils.util.LogUtil
import com.xwei.xchat.common.abstract.ResultCallBack
import com.xwei.xchat.common.network.Resource

/**
 * @desc
 * @author wei
 * @date  2022/1/3
 **/
 abstract class NetworkRequest<ResultType> {

    private val result: MutableLiveData<Resource<ResultType>> by lazy { MediatorLiveData() }

    init {
        result.postValue(Resource.loading(null))
        createRequest(object : ResultCallBack<LiveData<ResultType>>() {
            override fun onError(error: Int, errorMsg: String?) {
                result.postValue(Resource.error(error, errorMsg, null))
            }

            override fun onSuccess(value: LiveData<ResultType>?) {
                LogUtil.e("NetworkRequest", "value : ${value?.value.toString()}")
                val user = value?.value
                LogUtil.e("NetworkRequest", "user : ${user.toString()}")
                result.postValue(Resource.success(user))
            }
        })
    }

    abstract fun createRequest(callback: ResultCallBack<LiveData<ResultType>>)

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }
}