package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillJsonactivityBinding
import com.logisticsmanagementcr.android.adapter.WayBillAdapter
import com.logisticsmanagementcr.android.adapter.WayBillDisplay
import com.logisticsmanagementcr.android.network.JsonBillRecord
import com.logisticsmanagementcr.android.network.JsonBillService
import com.logisticsmanagementcr.android.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//获取JSON格式的公司运单，使用Retrofit进行请求与解析
class CompanyWayBillJSONActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyWayBillJsonactivityBinding
    private val billList = ArrayList<WayBillDisplay>() // 存储用于输出的运单信息


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyWayBillJsonactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //返回按钮
        binding.backButton.setOnClickListener {
            finish()
        }
        //界面创建就进行请求并解析
        val jsonBillService = ServiceCreator.create<JsonBillService>()
        jsonBillService.getAppData().enqueue(object : Callback<JsonBillRecord> {
            override fun onResponse(
                call: Call<JsonBillRecord>,
                response: Response<JsonBillRecord>
            ) {
                val jsonBillList = response.body()
                //正确获得信息，就将信息格式化并添加到billList中
                if (jsonBillList != null) {
                    for (bill in jsonBillList.waybillRecord) {
                        val billNo = "No: ${bill.waybillNo}"
                        val billTrace =
                            "${bill.transportationArrivalStation} - ${bill.transportationDepartureStation}  ${bill.goodsName} ${bill.numberOfPackages}件  到付${bill.freightPaidByTheReceivingParty}元"
                        val billName = "收货人：${bill.consignee}(${bill.consigneePhoneNumber})"
                        billList.add(WayBillDisplay(billNo, billTrace, billName))
                    }
                    showResponse(billList)
                }
            }

            override fun onFailure(call: Call<JsonBillRecord>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    //在recyclerView中显示数据，使用runOnUiThread来在主进程中进行操作
    private fun showResponse(billList: ArrayList<WayBillDisplay>) {
        runOnUiThread {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layoutManager
            val adapter = WayBillAdapter(billList)
            binding.recyclerView.adapter = adapter
        }
    }
}