package com.zelix.biznesplanuzb.retrofit.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zelix.biznesplanuzb.models.Click
import com.zelix.biznesplanuzb.models.ClickReq
import com.zelix.biznesplanuzb.repository.MyRepository
import com.zelix.biznesplanuzb.retrofit.api.ApiClient
import com.zelix.biznesplanuzb.retrofit.resource.Resource
import com.zelix.biznesplanuzb.util.LiveEvent
import com.zelix.biznesplanuzb.util.NetworkHelper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private var myRepository = MyRepository(ApiClient.apiService)
    private var liveData = MutableLiveData<Resource<ClickReq>>()

    val a = liveData.toSingleEvent()


    fun getPaidStatus(context: Context, click: Int): LiveData<Resource<ClickReq>> {


        viewModelScope.launch {
            if (NetworkHelper(context).isNetworkConnected()) {
                coroutineScope {
                    try {
                        liveData.postValue(Resource.loading(null))
                        val video = myRepository.getPaidStatus(click)

                        if (video.isSuccessful) {
                            liveData.postValue(Resource.success(video.body()))
                        } else {
                            liveData.postValue(
                                Resource.error(
                                    video.raw().toString(),
                                    null
                                )
                            )


                        }


                    } catch (e: Exception) {
                        liveData.postValue(Resource.error(e.message ?: "Error", null))
                    }
                }
            } else {
                liveData.postValue(
                    Resource.error(
                        "Internet no connection! Please, connect internet and try again!",
                        null
                    )
                )

            }

        }

        return liveData

    }

    private fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
        val result = LiveEvent<T>()
        result.addSource(this) {
            result.value = it
        }
        return result
    }

}

