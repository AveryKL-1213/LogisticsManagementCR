package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
                val billNo = "No: ${bill.waybillNo}"
                val fee: String = if (bill.freightPaidByTheReceivingParty == "")
                    "0"
                else
                    bill.freightPaidByTheReceivingParty
                val phone: String = if (bill.consigneePhoneNumber == "")
                    ""
                else
                    "(${bill.consigneePhoneNumber})"
                val receiver = if (bill.consignee == "")
                    "未填写"
                else
                    bill.consignee
                val billTrace =
                    "${bill.transportationArrivalStation} - 沈阳  ${bill.goodsName} ${bill.numberOfPackages}件  到付${fee}元"
                val billName = "收货人：${receiver}$phone"
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