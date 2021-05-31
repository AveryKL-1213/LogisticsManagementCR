package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillJsonactivityBinding
import com.logisticsmanagementcr.android.model.WayBillAdapter
import com.logisticsmanagementcr.android.model.WayBillDisplay
import com.logisticsmanagementcr.android.network.JsonBillRecord
import com.logisticsmanagementcr.android.network.JsonBillService
import com.logisticsmanagementcr.android.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyWayBillJSONActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyWayBillJsonactivityBinding
    private val billList = ArrayList<WayBillDisplay>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyWayBillJsonactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }
        val jsonBillService = ServiceCreator.create<JsonBillService>()
        jsonBillService.getAppData().enqueue(object : Callback<JsonBillRecord> {
            override fun onResponse(
                call: Call<JsonBillRecord>,
                response: Response<JsonBillRecord>
            ) {
                val jsonBillList = response.body()
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

    private fun showResponse(billList: ArrayList<WayBillDisplay>) {
        runOnUiThread {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layoutManager
            val adapter = WayBillAdapter(billList)
            binding.recyclerView.adapter = adapter
        }
    }
}