package com.zelix.biznesplanuzb.repository

import com.zelix.biznesplanuzb.models.Click
import com.zelix.biznesplanuzb.retrofit.api.ApiClient
import com.zelix.biznesplanuzb.retrofit.api.ApiService

class MyRepository(private var apiService: ApiService) {

    suspend fun getPaidStatus(click:Int) = apiService.getPaidStatus(click)

}