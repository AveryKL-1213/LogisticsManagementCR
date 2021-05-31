package com.logisticsmanagementcr.android

import android.content.Context
import android.content.Intent

//使用reified泛型实化简化启动activity的操作
inline fun <reified T> myStartActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}