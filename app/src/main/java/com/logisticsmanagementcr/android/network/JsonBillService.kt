package com.logisticsmanagementcr.android.network

import retrofit2.Call
import retrofit2.http.GET

interface JsonBillService {

    @GET("simulated-Waybills-db.json")
    fun getAppData(): Call<JsonBillRecord>

}