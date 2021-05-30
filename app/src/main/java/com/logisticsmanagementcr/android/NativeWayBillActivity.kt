package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.WayBill
import com.logisticsmanagementcr.android.databinding.ActivityNativeWayBillBinding
import kotlin.concurrent.thread

class NativeWayBillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNativeWayBillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeWayBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }


        val wayBillDao = AppDatabase.getDatabase(this).waybillDao()
        lateinit var nativeWayBilList: List<WayBill>
        var data = ""
        thread {
            nativeWayBilList = wayBillDao.loadAllBills()
            for (bill in nativeWayBilList) {
                data += "${bill.waybillNo} ${bill.consignee}\n"
            }
            showResponse(data)
        }


    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.responseText.text = response
        }
    }
}