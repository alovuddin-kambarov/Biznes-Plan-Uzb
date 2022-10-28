package com.zelix.biznesplanuzb.retrofit.api

import com.zelix.biznesplanuzb.models.ClickReq
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("check_pay.php")
    suspend fun getPaidStatus(
        @Field("merchant_trans_id") id:Int
    ): Response<ClickReq>


}


