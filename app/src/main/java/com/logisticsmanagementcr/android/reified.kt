package com.logisticsmanagementcr.android

import android.content.Context
import android.content.Intent

inline fun <reified T> myStartActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}