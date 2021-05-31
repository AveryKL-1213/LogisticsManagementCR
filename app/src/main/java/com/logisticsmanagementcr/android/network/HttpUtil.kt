package com.logisticsmanagementcr.android.network

import okhttp3.OkHttpClient
import okhttp3.Request

//使用OkHttp发送请求的单例类
object HttpUtil {

    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }

}