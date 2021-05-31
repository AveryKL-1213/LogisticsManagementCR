package com.logisticsmanagementcr.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//作为所有activity的基类，便于处理Activity的销毁与定位当前Activity
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity", javaClass.simpleName)
        ActivityBox.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityBox.removeActivity(this)
    }
}