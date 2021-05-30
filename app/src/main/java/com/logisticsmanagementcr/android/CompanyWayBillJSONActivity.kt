package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillJsonactivityBinding
import com.logisticsmanagementcr.android.network.JsonBillRecord
import com.logisticsmanagementcr.android.network.JsonBillService
import com.logisticsmanagementcr.android.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyWayBillJSONActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyWayBillJsonactivityBinding.inflate(layoutInflater)
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
                        Log.d("Test", "${bill.waybillNo} ${bill.consignee}")
                    }
                }
            }

            override fun onFailure(call: Call<JsonBillRecord>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}