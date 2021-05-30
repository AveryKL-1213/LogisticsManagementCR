package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.WayBill
import com.logisticsmanagementcr.android.databinding.ActivityNativeWayBillBinding
import kotlin.concurrent.thread

class NativeWayBillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNativeWayBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }


        val wayBillDao = AppDatabase.getDatabase(this).waybillDao()
        lateinit var nativeWayBilList: List<WayBill>
        thread {
            nativeWayBilList = wayBillDao.loadAllBills()
            for (bill in nativeWayBilList) {
                Log.d("Test", "${bill.waybillNo} ${bill.consignee}")
            }
        }


    }
}