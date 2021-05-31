package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.WayBill
import com.logisticsmanagementcr.android.databinding.ActivityNativeWayBillBinding
import com.logisticsmanagementcr.android.model.WayBillAdapter
import com.logisticsmanagementcr.android.model.WayBillDisplay
import kotlin.concurrent.thread

class NativeWayBillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNativeWayBillBinding
    private val billList = ArrayList<WayBillDisplay>()

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
        thread {
            nativeWayBilList = wayBillDao.loadAllBills()
            for (bill in nativeWayBilList) {
                Log.d("Test", "${bill.waybillNo} ${bill.consignee}\n")
                val billNo = "No: ${bill.waybillNo}"
                val billTrace =
                    "${bill.transportationArrivalStation} - 沈阳  ${bill.goodsName} ${bill.numberOfPackages}件  到付${bill.freightPaidByTheReceivingParty}元"
                val billName = "收货人：${bill.consignee}(${bill.consigneePhoneNumber})"
                billList.add(WayBillDisplay(billNo, billTrace, billName))
            }
            showBill()
        }
    }

    private fun showBill() {
        runOnUiThread {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layoutManager
            val adapter = WayBillAdapter(billList)
            binding.recyclerView.adapter = adapter
        }
    }
}