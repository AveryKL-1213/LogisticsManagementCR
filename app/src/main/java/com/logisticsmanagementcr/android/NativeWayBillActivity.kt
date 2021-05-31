package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.WayBill
import com.logisticsmanagementcr.android.databinding.ActivityNativeWayBillBinding
import com.logisticsmanagementcr.android.adapter.WayBillAdapter
import com.logisticsmanagementcr.android.adapter.WayBillDisplay
import kotlin.concurrent.thread

//查询本机运单Activity
class NativeWayBillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNativeWayBillBinding
    private val billList = ArrayList<WayBillDisplay>() // 存储用于输出的运单信息

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeWayBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //返回按钮
        binding.backButton.setOnClickListener {
            finish()
        }
        // 界面创建就开始在数据库中取数据
        val wayBillDao = AppDatabase.getDatabase(this).waybillDao()
        lateinit var nativeWayBilList: List<WayBill>
        thread {
            nativeWayBilList = wayBillDao.loadAllBills()
            for (bill in nativeWayBilList) {
                val billNo = "No: ${bill.waybillNo}"
                //需要显示的数据为空，未填写时，使用默认值进行显示
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
                billList.add(WayBillDisplay(billNo, billTrace, billName)) //添加格式化后的信息到billList
            }
            showBill()
        }
    }

    //在recyclerView中显示数据，使用runOnUiThread来在主进程中进行操作
    private fun showBill() {
        runOnUiThread {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layoutManager
            val adapter = WayBillAdapter(billList)
            binding.recyclerView.adapter = adapter
        }
    }
}