package com.logisticsmanagementcr.android.network

import retrofit2.Call
import retrofit2.http.GET

//使用Retrofit所用的服务器接口
interface JsonBillService {

    @GET("simulated-Waybills-db.json")
    fun getAppData(): Call<JsonBillRecord>

}